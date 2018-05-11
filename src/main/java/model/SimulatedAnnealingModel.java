package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Random;

public class SimulatedAnnealingModel implements AlgorithmModel {

    private LongTransition<int[]> transition;
    private EvaluateFunction<int[]> evaluateFunction;

    private int bitCount;
    private int score;
    private double temperature = 1;
    private double deltaScale;
    private int[] bestObj;
    private int maxIteration;

    private Random random = new Random();
    private int[] current;
    private int[] tempAry;


    SimulatedAnnealingModel(int bitCount, int iteration, double startTemperature) {
        this.bitCount = bitCount;
        maxIteration = iteration;

        this.deltaScale = 1d - 1d / startTemperature;
//        temperature = 10;
//        deltaScale = 0.99;

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
        int count = 0;

        while (count < maxIteration && score < maxScore) {
            iterateOnce();
            count++;
        }

    }

    @Override
    public void iterateOnce() {
        transition.neighbor(current, tempAry);
        int score2 = (int) evaluateFunction.evaluate(tempAry);

        if (determination(score, score2)) {
            System.arraycopy(tempAry, 0, bestObj, 0, bitCount);
            System.arraycopy(tempAry, 0, current, 0, bitCount);
            score = score2;
        }

        temperature *= deltaScale;
    }

    private boolean determination(int current, int neighbor) {
        if (neighbor >= current) {
            return true;
        } else {
            double p = random.nextDouble();
            double annealing = Math.exp((neighbor - current) / temperature);
            return p < annealing;
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
