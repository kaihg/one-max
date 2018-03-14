package model;

import evaluator.EvaluateFunction;
import evaluator.EvaluatorFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;
import transit.TransitFactory;
import transit.TransitType;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class ExhaustionModelTest {

    @Test
    public void when_3bit_then_resultIs111() {

        AlgorithmModel<Long> model = initModel(3);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("111",result);
    }

    @Test
    public void when_10bit_then_resultIs1111111111() {

        AlgorithmModel<Long> model = initModel(10);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("1111111111",result);
    }

    @Test
    public void when_20bit_then_resultIs20times() {

        AlgorithmModel<Long> model = initModel(20);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("11111111111111111111",result);
    }

    @Test
    public void when_30bit_then_resultIs30times() {

        AlgorithmModel<Long> model = initModel(30);

        model.start();
        String result = model.getResult();

        Assert.assertEquals("111111111111111111111111111111",result);
    }

    private AlgorithmModel<Long> initModel(int bitNumber){
        Iterator<Long> transit = TransitFactory.createLongIterator(TransitType.INCREASE, Optional.of("0"),bitNumber);
        EvaluateFunction<Long> evaluateFunction = EvaluatorFactory.createLongEvaluator();

        AlgorithmModel<Long> model = new ExhaustionModel();
        model.setEvaluator(evaluateFunction);
        model.setTransit(transit);

        return model;
    }


}
