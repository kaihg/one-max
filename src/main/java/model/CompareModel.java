package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

import java.util.Random;

public class CompareModel implements AlgorithmModel {

    private AlgorithmModel[] models;

    private int iteration;
    private StringBuilder resultRecord;
    private String[] compareModelNames;
    private int initSeed;

    public CompareModel(AlgorithmModel[] models, int iteration, String[] compareModelNames) {
        this.models = models;
        this.iteration = iteration;
        this.compareModelNames = compareModelNames;
        resultRecord = new StringBuilder();
    }

    @Override
    public void init(int seed) {
        Random random = new Random(seed);
        int childSeed = random.nextInt();
        for (AlgorithmModel model : models) {
            model.init(childSeed);
        }
    }

    @Override
    public void setTransit(LongTransition transit) {

    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {

    }

    public void setSeed(int seed) {
        this.initSeed = seed;
    }

    @Override
    public void start() {
        if (this.initSeed != 0) {
            System.out.println("init seed : " + initSeed);
            this.init(initSeed);
        } else {
            Random random = new Random();
            int childSeed = random.nextInt();
            System.out.println("init seed : " + childSeed);
            this.init(childSeed);
        }


        for (int i = 0; i < this.compareModelNames.length; i++) {
            this.resultRecord.append(compareModelNames[i]);
            if (i < this.compareModelNames.length - 1) {
                this.resultRecord.append(",");
            } else {
                this.resultRecord.append("\n");
            }
        }
        saveAllModelResult(0);
        for (int i = 0; i < iteration; i++) {
            iterateOnce();
            saveAllModelResult(i + 1);
        }

        System.out.println();
        System.out.print(resultRecord.toString());

    }

    @Override
    public void iterateOnce() {
        for (AlgorithmModel model : models) {
            model.iterateOnce();
        }
    }

    private void saveAllModelResult(int index) {
        resultRecord.append(index).append(",");
        for (AlgorithmModel model : models) {
            resultRecord.append(model.getScore()).append(",");
        }
        resultRecord.deleteCharAt(resultRecord.length() - 1); //砍掉最後的逗號
        resultRecord.append("\n");
    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public double getScore() {
        return 0;
    }
}
