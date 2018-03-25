package model;

import evaluator.EvaluateFunction;
import evaluator.EvaluatorFactory;
import transition.BestNeighborTransition;
import transition.LongTransition;
import transition.TransitFactory;
import transition.TransitType;

import java.util.Optional;

public class ModelFactory {

    public static final String EXHAUSTION_SEARCH = "es";
    public static final String HILL_CLIMBING = "hc";
    public static final String SIMULATED_ANNEALING = "sa";

    public static AlgorithmModel createModel(String algorithm, int bitCount, int runTimes, int iterationCount, int neighborPickCount) {
        AlgorithmModel model;
        LongTransition transition;
        EvaluateFunction function;
        switch (algorithm) {
            case EXHAUSTION_SEARCH:
                model = new ExhaustionModel();
                transition = TransitFactory.createLongIterator(TransitType.INCREASE, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case HILL_CLIMBING:
            default:
                model = new HillClimbingModel(bitCount, iterationCount);
                transition = TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case SIMULATED_ANNEALING:
                model = new SimulatedAnnealingModel(bitCount, iterationCount, iterationCount);
                transition = TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
        }

        if (neighborPickCount > 1) {
            transition = new BestNeighborTransition(transition, neighborPickCount, function);
        }

        if (runTimes > 1) {
            AlgorithmModel[] models = new AlgorithmModel[runTimes];
            for (int i = 0; i < runTimes; i++) {
                models[i] = createModel(algorithm, bitCount, 1, iterationCount, neighborPickCount);
            }
            model = new RepeatModel(models, runTimes);
            return model;
        }


        model.setEvaluator(function);
        model.setTransit(transition);

        return model;
    }

//    public static AlgorithmModel createRepeatModel(String algorithm, int bitCount, int runTimes, int iterationCount, int neighborPickCount) {
//        AlgorithmModel model;
//        LongTransition transition;
//        EvaluateFunction function;
//
//
//        if (runTimes > 1) {
//            AlgorithmModel[] models = new AlgorithmModel[runTimes];
//            for (int i = 0; i < runTimes; i++) {
//                models[i] = createModel(algorithm,bitCount,1,iterationCount,neighborPickCount);
//            }
//            model = new RepeatModel(models,runTimes);
//        }else{
//            return ModelFactory.createModel(algorithm,bitCount,runTimes,iterationCount,neighborPickCount);
//        }
//
//        if (neighborPickCount > 1) {
//            transition = new BestNeighborTransition(transition, neighborPickCount, function);
//        }
//
//        model.setEvaluator(function);
//        model.setTransit(transition);
//
//        return model;
//    }

}
