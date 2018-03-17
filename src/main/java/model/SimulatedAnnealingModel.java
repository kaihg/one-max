package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class SimulatedAnnealingModel implements AlgorithmModel {

    private LongTransition transition;
    private EvaluateFunction<int[]> evaluateFunction;

    private int bitCount;
    private double temperature = 1;
    private double deltaScale;
    private int[] bestObj;

    private Random random = new Random();

    SimulatedAnnealingModel(int bitCount, int startTemperature) {
        this.bitCount = bitCount;
        this.deltaScale = 1d - 1d / startTemperature;
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
        int maxScore = bitCount;

        double minTemper = 0.0001;
        int count = 0;

        while (temperature > minTemper && score < maxScore) {
            transition.neighbor(current, tempAry);
            int score2 = evaluateFunction.evaluate(tempAry);

            if (determination(score, score2)) {
                bestObj = Arrays.copyOf(tempAry, tempAry.length);
                score = score2;
                int[] swap = current;
                current = tempAry;
                tempAry = swap;
            }

            temperature *= deltaScale;

        }

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
