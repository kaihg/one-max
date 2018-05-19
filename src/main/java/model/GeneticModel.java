package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Random;

public class GeneticModel implements AlgorithmModel {


    private final int iteration;
    LongTransition<int[][]> transition;
    EvaluateFunction evaluator;

    protected int[][] current;
    private int[][] fitPopulation;
    private int[][] temp;
    private double[] scoreMap;
    private int[] cdf;
    private int bitCount;
    protected int tourmentRepeatTimes = 1;

    protected Random random;

    public GeneticModel(int iteration, int populationSize, int bitCount) {
        this.iteration = iteration;

        this.current = new int[populationSize][bitCount];
        this.fitPopulation = new int[populationSize][bitCount];
        this.temp = new int[populationSize][bitCount];
        this.scoreMap = new double[populationSize];
        this.cdf = new int[populationSize];


        this.bitCount = bitCount;

    }

    @Override
    public void init(int seed) {
        this.random = new Random(seed);
        for (int i = 0; i < current.length; i++) {
            int[] item = current[i];
            for (int j = 0; j < bitCount; j++) {
                item[j] = random.nextInt(2);
            }
        }
    }

    @Override
    public void setTransit(LongTransition transit) {
        this.transition = transit;
    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public void start() {
        Random random = new Random();
        int childSeed = random.nextInt();
        this.init(childSeed);

        for (int i = 0; i < iteration; i++) {
            iterateOnce();
        }

    }

    @Override
    public void iterateOnce() {
        double[] fitnessScores = fitnessFunction(current);
        int[][] selected = select(current, fitnessScores);

        transition.neighbor(selected, temp);

        changeCurrentPopulation(temp, current);
    }

    protected void changeCurrentPopulation(int[][] temp, int[][] current) {
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, current[i], 0, temp[i].length);
        }
    }


    private double[] fitnessFunction(int[][] population) {
        for (int i = 0; i < population.length; i++) {
            scoreMap[i] = calculateFitnessScore(population[i], i);
        }
        return scoreMap;
    }

    protected double calculateFitnessScore(int[] gene, int index) {
        return evaluator.evaluate(gene);
    }

    private int[][] select(int[][] population, double[] fitnessScores) {
        // roulette or tournament

        return tournamentSelection(population, tourmentRepeatTimes, fitnessScores);

    }

    private int[][] rouletteWheelSelection(int[][] population, int[] fitnessScores) {
        int sum = 0;
        for (int fitnessScore : fitnessScores) {
            sum += fitnessScore;
        }

        cdf[0] = fitnessScores[0];
        for (int i = 1; i < population.length; i++) {
            cdf[i] = cdf[i - 1] + fitnessScores[i];
        }

        for (int i = 0; i < population.length; i++) {
            fitPopulation[i] = shootRoulette(population, sum, cdf);
        }

        return fitPopulation;
    }

    private int[] shootRoulette(int[][] population, int sum, int[] cdf) {
        int shoot = random.nextInt(sum);
        for (int i = 0; i < cdf.length; i++) {
            if (shoot < cdf[i]) {
                return population[i];
            }
        }

        return population[0];
    }

    private int[][] tournamentSelection(int[][] population, int repeat, double[] scoreMap) {

        for (int i = 0; i < fitPopulation.length; i++) {
            int winner = random.nextInt(population.length);

            for (int j = 0; j < repeat; j++) {
                int competitor = random.nextInt(population.length);
                winner = judgmentWinner(scoreMap, competitor, winner);
            }

            fitPopulation[i] = population[winner];
        }

        return fitPopulation;
    }

    protected int judgmentWinner(double[] scoreMap, int competitor, int winner) {
        return scoreMap[competitor] > scoreMap[winner] ? competitor : winner;
    }

    @Override
    public String getResult() {
        double[] sourceMap = fitnessFunction(current);
        int best = 0;
        for (int i = 1; i < sourceMap.length; i++) {
            best = judgmentWinner(sourceMap, i, best);
        }

        int[] bestObj = current[best];
        StringBuilder builder = new StringBuilder();
//        for (int i = bestObj.length - 1; i >= 0; i--) {
//            builder.append(bestObj[i]);
//        }
        for (int aBestObj : bestObj) {
            builder.append(aBestObj);
        }
        return builder.toString();
    }

    @Override
    public double getScore() {
        double[] sourceMap = fitnessFunction(current);
        int best = 0;
        for (int i = 1; i < sourceMap.length; i++) {
            if (sourceMap[i] > sourceMap[best]) {
                best = i;
            }
        }

        int[] bestObj = current[best];
        return evaluator.evaluate(bestObj);
    }
}
