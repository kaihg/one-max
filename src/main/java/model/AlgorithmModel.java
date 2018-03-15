package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

public interface AlgorithmModel {

    void init();

    void setTransit(LongTransition transit);

    void setEvaluator(EvaluateFunction evaluator);


    void start();
    String getResult();

}
