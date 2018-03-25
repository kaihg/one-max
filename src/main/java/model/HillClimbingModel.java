package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Random;
import java.util.stream.IntStream;

public class HillClimbingModel implements AlgorithmModel{

    private LongTransition transition;
    private EvaluateFunction<int[]> evaluateFunction;

    private int bitCount;
    private int iterationTimes;
    private int[] bestObj;

    //    private int[] current;
    private int[] tempAry;
    private int score;

    public HillClimbingModel(int bitCount, int iterationTimes) {
        this.bitCount = bitCount;
        this.iterationTimes = iterationTimes;

        bestObj = new int[bitCount];
        tempAry = new int[bitCount];
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

        score = evaluateFunction.evaluate(current);
        int maxScore = bitCount;

        for (int i = 0; i < iterationTimes; i++) {
            this.iterateOnce(current);

            if (score == maxScore) {
                break;
            }
        }
    }

    @Override
    public void iterateOnce(int[] current) {
        // find better neighbor with "bitCount" times at most
        transition.neighbor(current, tempAry);

        int score2 = evaluateFunction.evaluate(tempAry);
        if (score2 > score) {
            System.arraycopy(tempAry, 0, bestObj, 0, bitCount);
            System.arraycopy(tempAry, 0, current, 0, bitCount);
            score = score2;
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
