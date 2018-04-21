package model;

import evaluator.EvaluateFunction;
import transition.LongTransition;

public class CompareModel implements AlgorithmModel {

    private AlgorithmModel[] models;

    private int iteration;
    private StringBuilder resultRecord;
    private String[] compareModelNames;

    public CompareModel(AlgorithmModel[] models, int iteration, String[] compareModelNames) {
        this.models = models;
        this.iteration = iteration;
        this.compareModelNames = compareModelNames;
        resultRecord = new StringBuilder();
    }

    @Override
    public void init() {
        for (AlgorithmModel model : models) {
            model.init();
        }
    }

    @Override
    public void setTransit(LongTransition transit) {

    }

    @Override
    public void setEvaluator(EvaluateFunction evaluator) {

    }

    @Override
    public void start() {
        this.init();

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
    public int getScore() {
        return 0;
    }
}
