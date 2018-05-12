package model;

import evaluator.EvaluatorFactory;
import org.junit.Assert;
import org.junit.Test;
import transition.TransitFactory;
import transition.TransitType;

import java.util.Optional;

public class RepeatModelTest {

    @Test
    public void repeat_HC_model_30_start() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.HILL_CLIMBING, 100, 30, 500, 1);
        model.start();

        Assert.assertEquals(100, model.getScore(), 0);
    }

    @Test
    public void repeat_SA_model_30_start() {
        AlgorithmModel model = ModelFactory.createModel(ModelFactory.SIMULATED_ANNEALING, 100, 30, 500, 1);
        model.start();

        Assert.assertEquals(100, model.getScore(), 0);
    }


    @Test
    public void allInitialWithRandom() {
        int bitCount = 100;
        AlgorithmModel[] models = new AlgorithmModel[10];
        for (int i = 0; i < 10; i++) {
            models[i] = new HillClimbingModel(bitCount, 100);
            models[i].setEvaluator(EvaluatorFactory.createLongEvaluator());
            models[i].setTransit(TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount));
        }

        RepeatModel model = new RepeatModel(models);

        Assert.assertNotEquals(models[0], models[1]);

        model.init(0);
        Assert.assertNotEquals(models[0].getScore(), models[1].getScore());

        model.setTransit(TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount));
        Assert.assertNotEquals(models[0].getScore(), models[1].getScore());
        model.init(0);
        Assert.assertNotEquals(models[0].getScore(), models[1].getScore());
    }
}
