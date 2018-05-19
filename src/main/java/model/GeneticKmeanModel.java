package model;

import java.util.Random;

public class GeneticKmeanModel extends GeneticModel {


//    private final int[][] fitPopulation;
//    private final int[][] temp;
//    private final int[] scoreMap;
//    private final int[] cdf;

//    private LongTransition transit;
//    private EvaluateFunction evaluator;

    private KMeanModel[] clusters;

    private int populationSize;
    private int groupNum;
    private int attributes;
    private double[][] datas;

    public GeneticKmeanModel(int iteration, int populationSize, double[][] datas, int k) {
        super(iteration, populationSize, datas.length);

        this.attributes = datas[0].length;
        this.datas = datas;
        this.groupNum = k;
        this.populationSize = populationSize;

        clusters = new KMeanModel[populationSize];
//        tourmentRepeatTimes = 4;
    }

    @Override
    public void init(int seed) {
        random = new Random(seed);

        // 產生random的 population
        for (int i = 0; i < populationSize; i++) {
            int[] gene = current[i];
            for (int j = 0; j < gene.length; j++) {
                gene[j] = random.nextInt(groupNum);
            }

            clusters[i] = new EuclideanClusterModel(datas);
            clusters[i].setClusters(gene, groupNum);
            clusters[i].updateGroupCenter();
        }

    }

    protected int judgmentWinner(double[] scoreMap, int competitor, int winner) {
        return scoreMap[competitor] < scoreMap[winner] ? competitor : winner;
    }

    protected double calculateFitnessScore(int[] gene, int index) {

        return evaluator.evaluate(clusters[index]);
    }

    protected void changeCurrentPopulation(int[][] temp, int[][] current) {
        for (int i = 0; i < temp.length; i++) {
            System.arraycopy(temp[i], 0, current[i], 0, temp[i].length);

            clusters[i].updateClusters(current[i]);
            clusters[i].updateGroupCenter();
        }
    }

//
//    @Override
//    public String getResult() {
//        return null;
//    }
}
