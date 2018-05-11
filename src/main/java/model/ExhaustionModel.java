package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Arrays;

public class ExhaustionModel implements AlgorithmModel {

    private LongTransition<int[]> transit;
    private EvaluateFunction<int[]> evalutor;

    private int[] bestObj = {};
    private int bestScore;

    @Override
    public void init(int seed) {
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
        init(0);

        while(transit.hasNext()){
            int[] nextObj = transit.next();
            int score = (int) evalutor.evaluate(nextObj);
            if (score > this.bestScore){
                this.bestObj = Arrays.copyOf(nextObj, nextObj.length);
                this.bestScore = score;
            }
        }
    }

    @Override
    public void iterateOnce() {

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
    public double getScore() {
        return bestScore;
    }
}
