package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Random;
import java.util.stream.IntStream;

public class SimulatedAnnealingModel implements AlgorithmModel {

    private LongTransition transition;
    private EvaluateFunction<int[]> evaluateFunction;

    private int bitCount;
    private int score;
    private double temperature = 1;
    private double deltaScale;
    private int[] bestObj;
    private int maxIteration;

    private Random random = new Random();
    private int[] tempAry;


    SimulatedAnnealingModel(int bitCount, int iteration, int startTemperature) {
        this.bitCount = bitCount;
        maxIteration = iteration;
        this.deltaScale = 1d - 1d / startTemperature;

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
//        int[] tempAry = new int[bitCount];

        score = evaluateFunction.evaluate(current);
        int maxScore = bitCount;

//        double minTemper = 0.0001;
        int count = 0;

        while (count < maxIteration && score < maxScore) {
            iterateOnce(current);
            count++;
        }

    }

    @Override
    public void iterateOnce(int[] current) {
        transition.neighbor(current, tempAry);
        int score2 = evaluateFunction.evaluate(tempAry);

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
    public int getScore() {
        return IntStream.of(bestObj).sum();
    }
}
