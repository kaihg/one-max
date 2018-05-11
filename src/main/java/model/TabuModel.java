package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Arrays;
import java.util.Random;

public class TabuModel implements AlgorithmModel {

    private int maxIteration;
    private int memorySize;
    private int bitCount;

    private int score;
    private int[] tempAry;
    private int[] bestObj;

    private LongTransition<int[]> transition;
    private EvaluateFunction<int[]> evaluate;


    public TabuModel(int bitCount, int iteration) {
        maxIteration = iteration;
        this.bitCount = bitCount;

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
        bestObj = Arrays.copyOf(transition.next(), bitCount);
        score = (int) evaluate.evaluate(bestObj);
    }

    @Override
    public void setTransit(LongTransition transit) {
        this.transition = transit;
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
        this.evaluate = evaluator;
    }

    @Override
    public void start() {
        Random random = new Random();
        int childSeed = random.nextInt();
        this.init(childSeed);

        for (int i = 0; i < maxIteration; i++) {
            iterateOnce();

            if (score == evaluate.maxScore(tempAry)) {
                break;
            }
        }
    }

    @Override
    public void iterateOnce() {
        transition.neighbor(bestObj, tempAry);

        int score2 = (int) evaluate.evaluate(tempAry);

        if (score2 > score) {
            System.arraycopy(tempAry, 0, bestObj, 0, bitCount);
            score = score2;
            transition.next();
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
