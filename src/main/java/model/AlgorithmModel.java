package model;

import evaluator.EvaluateFunction;

import java.util.Iterator;

public interface AlgorithmModel<T> {

    void init();

    void setTransit(Iterator<T> transit);
    void setEvaluator(EvaluateFunction<T> evaluator);


    void start();
    String getResult();

}
