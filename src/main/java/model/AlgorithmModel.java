package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

public interface AlgorithmModel {

    void init(int seed);

    void setTransit(LongTransition transit);

    void setEvaluator(EvaluateFunction evaluator);

    /**
     * Computer the final result for iteration.
     */
    void start();

    /**
     * Just compute once
     */
    void iterateOnce();

    String getResult();

    double getScore();

}
