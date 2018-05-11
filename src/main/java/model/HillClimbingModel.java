package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Random;

public class HillClimbingModel implements AlgorithmModel{

    private LongTransition<int[]> transition;
    private EvaluateFunction<int[]> evaluateFunction;

    private int bitCount;
    private int iterationTimes;
    private int[] bestObj;

    private int[] current;
    private int[] tempAry;
    private int score;

    public HillClimbingModel(int bitCount, int iterationTimes) {
        this.bitCount = bitCount;
        this.iterationTimes = iterationTimes;

        bestObj = new int[bitCount];
        tempAry = new int[bitCount];
    }

    @Override
    public void init(int seed) {
        Random random = new Random(seed);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bitCount; i++) {
            builder.append(random.nextInt(2));
        }
        transition.setDefaultValue(builder.toString());

        current = transition.next();
        score = (int) evaluateFunction.evaluate(current);
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
        Random random = new Random();
        int childSeed = random.nextInt();
        this.init(childSeed);

        int maxScore = evaluateFunction.maxScore(this.tempAry);

        for (int i = 0; i < iterationTimes; i++) {
            this.iterateOnce();

            if (score == maxScore) {
                break;
            }
        }
    }

    @Override
    public void iterateOnce() {
        // find better neighbor with "bitCount" times at most
        transition.neighbor(current, tempAry);

        int score2 = (int) evaluateFunction.evaluate(tempAry);
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
    public double getScore() {
        return score;
    }
}
