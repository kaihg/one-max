package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Arrays;

public class ExhaustionModel implements AlgorithmModel {

    private LongTransition transit;
    private EvaluateFunction<int[]> evalutor;

    private int[] bestObj = {};
    private int bestScore;

    @Override
    public void init() {
        bestScore = 0;
    }

    @Override
    public void setTransit(LongTransition transit) {
        this.transit = transit;
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
        this.evalutor = evaluator;
    }

    @Override
    public void start() {
        init();

        while(transit.hasNext()){
            int[] nextObj = transit.next();
            int score = evalutor.evaluate(nextObj);
            if (score > this.bestScore){
                this.bestObj = Arrays.copyOf(nextObj, nextObj.length);
                this.bestScore = score;
            }
        }
    }

    @Override
    public String getResult() {
//        return Long.toBinaryString(this.bestObj);
        StringBuilder builder = new StringBuilder();
        for (int i = bestObj.length - 1; i >= 0; i--) {
            builder.append(bestObj[i]);
        }
        return builder.toString();
    }

    @Override
    public int getScore() {
        return bestScore;
    }
}
