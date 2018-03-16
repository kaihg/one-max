package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class HillClimbingModel implements AlgorithmModel{

    private LongTransition transition;
    private EvaluateFunction<int[]> evaluateFunction;

    private int bitCount;
    private int iterationTimes;
    private int[] bestObj;

    public HillClimbingModel(int bitCount, int iterationTimes) {
        this.bitCount = bitCount;
        this.iterationTimes = iterationTimes;
    }

    @Override
    public void init() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bitCount; i++) {
            builder.append(random.nextInt(2));
        }
        transition.setDefaultValue(builder.toString());

    }

    @Override
    public void setTransit(LongTransition transit) {
        this.transition = transit;
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
        this.evaluateFunction = evaluator;
    }

    @Override
    public void start() {
        init();

        int[] current = transition.next();
        int[] tempAry = new int[bitCount];

        int score = evaluateFunction.evaluate(current);

        for (int i = 0; i < iterationTimes; i++) {
            transition.neighbor(current,tempAry);
            int score2 = evaluateFunction.evaluate(tempAry);
            if (score2 > score){
                bestObj = Arrays.copyOf(tempAry,tempAry.length);
                score = score2;
                int[] swap = current;
                current = tempAry;
                tempAry = swap;

            }
        }
    }

    @Override
    public String getResult() {
        StringBuilder builder = new StringBuilder();
        for (int i = bestObj.length - 1; i >= 0; i--) {
            builder.append(bestObj[i]);
        }
        return builder.toString();
    }

    @Override
    public int getScore() {
        return IntStream.of(bestObj).sum();
    }
}
