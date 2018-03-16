package model;

import evaluator.EvaluateFunction;
import evaluator.EvaluatorFactory;
import transition.LongTransition;
import transition.TransitFactory;
import transition.TransitType;

import java.util.Optional;

public class ModelFactory {

    public static final String EXHAUSTION_SEARCH = "es";
    public static final String HILL_CLIMBING = "hc";

    public static AlgorithmModel createModel(String algorithm, int bitCount) {
        AlgorithmModel model;
        LongTransition transition;
        EvaluateFunction function;
        switch (algorithm) {
            case EXHAUSTION_SEARCH:
            default:
                model = new ExhaustionModel();
                transition = TransitFactory.createLongIterator(TransitType.INCREASE, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case HILL_CLIMBING:
                model = new HillClimbingModel(bitCount,1<<(bitCount/2));
                transition = TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
        }

        model.setEvaluator(function);
        model.setTransit(transition);

        return model;
    }

}
