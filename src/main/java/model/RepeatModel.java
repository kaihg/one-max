package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

public class RepeatModel implements AlgorithmModel {

    private AlgorithmModel model;
    private int repeatTimes;
    private String result = "";
    private int bestScore;

    public RepeatModel(AlgorithmModel model, int repeat) {
        this.model = model;
        this.repeatTimes = repeat;
    }

    @Override
    public void init() {
        this.model.init();
    }

    @Override
    public void setTransit(LongTransition transit) {
        this.model.setTransit(transit);
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
        this.model.setEvaluator(evaluator);
    }

    @Override
    public void start() {
        int score = 0;
        String result = "";
        for (int i = 0; i < repeatTimes; i++) {
            this.model.start();
            score += model.getScore();
        }

        this.bestScore = score / repeatTimes;
        this.result = result;
    }

    @Override
    public void iterateOnce(int[] current) {

    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public int getScore() {
        return bestScore;
    }
}
