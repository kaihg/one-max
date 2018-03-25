package model;

import evaluator.EvaluateFunction;
import evaluator.EvaluatorFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import transition.LongTransition;
import transition.TransitFactory;
import transition.TransitType;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ExhaustionModelTest {

    @Test
    public void when_3bit_then_resultIs111() {

        AlgorithmModel model = initModel(3);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("111",result);
    }

    @Test
    public void when_10bit_then_resultIs1111111111() {

        AlgorithmModel model = initModel(10);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("1111111111",result);
    }

    @Test
    public void when_20bit_then_resultIs20times() {

        AlgorithmModel model = initModel(20);

        long nano = System.nanoTime();
        model.start();
        nano = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nano);
        System.out.println(nano);
        Assert.assertTrue(nano < 50);
        String result = model.getResult();

        Assert.assertEquals("11111111111111111111",result);
    }

    @Ignore
    @Test
    public void when_30bit_then_resultIs30times() {

        AlgorithmModel model = initModel(30);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("111111111111111111111111111111",result);
    }

    private AlgorithmModel initModel(int bitNumber) {
        LongTransition transit = TransitFactory.createLongIterator(TransitType.INCREASE, Optional.of("0"), bitNumber);
        EvaluateFunction evaluateFunction = EvaluatorFactory.createLongEvaluator();

        AlgorithmModel model = new ExhaustionModel();
        model.setEvaluator(evaluateFunction);
        model.setTransit(transit);

        return model;
    }


}
