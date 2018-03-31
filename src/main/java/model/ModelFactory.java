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
    public static final String TABU_SEARCH = "ts";
    public static final String GENETIC_ALGORITHM = "ga";

    public static AlgorithmModel createModel(String algorithm, int bitCount, int runTimes, int iterationCount, int neighborPickCount, double... extraParams) {
        AlgorithmModel model = null;
        LongTransition transition = null;
        EvaluateFunction function = null;
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
                double temperature = extraParams.length < 1 ? bitCount : extraParams[0];
                model = new SimulatedAnnealingModel(bitCount, iterationCount, temperature);
                transition = TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case TABU_SEARCH:
                model = new TabuModel(bitCount, iterationCount);
                transition = TransitFactory.createLongIterator(TransitType.LAST_REJECT, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case GENETIC_ALGORITHM:
                model = new GeneticModel(iterationCount, (int) extraParams[0], bitCount);
                transition = TransitFactory.createGeneticTransition(extraParams[1], extraParams[2]);
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

    public static String getAlgorithmName(String shortCode) {
        switch (shortCode) {
            case EXHAUSTION_SEARCH:
                return "exhaustion search";
            case HILL_CLIMBING:
                return "hill climbing search";
            case SIMULATED_ANNEALING:
                return "simulated annealing";
            case TABU_SEARCH:
                return "tabu search";
            case GENETIC_ALGORITHM:
                return "Genetic Algorithm";
            default:
                return "Nothing";
        }
    }

}
