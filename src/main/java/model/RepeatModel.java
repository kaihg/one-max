package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

public class RepeatModel implements AlgorithmModel {

    private AlgorithmModel[] models;
    private int repeatTimes;

    public RepeatModel(AlgorithmModel[] models, int repeat) {
        this.models = models;
        this.repeatTimes = repeat;
    }

    public RepeatModel(AlgorithmModel[] models) {
        this.models = models;
        this.repeatTimes = models.length;
    }

    @Override
    public void init() {
        for (AlgorithmModel model : models) {
            model.init();
        }
    }

    @Override
    public void setTransit(LongTransition transit) {
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
    }

    @Override
    public void start() {
        for (AlgorithmModel model : models) {
            model.start();
        }

    }

    @Override
    public void iterateOnce() {
        for (AlgorithmModel model : models) {
            model.iterateOnce();
        }
    }

    @Override
    public String getResult() {
        return "";
    }

    @Override
    public int getScore() {
        double score = 0d;
        if (models != null) {
            for (AlgorithmModel model : models) {
                score += model.getScore();
            }
        }
        return (int) Math.round(score / repeatTimes);
    }
}
