package model;

import evaluator.EvaluateFunction;

import java.util.Iterator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ExhaustionModel implements AlgorithmModel<Long>{

    private Iterator<Long> transit;
    private EvaluateFunction<Long> evalutor;

    private Long bestObj;
    private int bestScore;

    @Override
    public void init() {
        bestScore = 0;
    }

    @Override
    public void setTransit(Iterator<Long> transit) {
        this.transit = transit;
    }

    @Override
    public void setEvaluator(EvaluateFunction<Long> evaluator) {
        this.evalutor = evaluator;
    }

    @Override
    public void start() {
        init();
//        LongStream test = LongStream.range(0,1);


        while(transit.hasNext()){
            Long nextObj = transit.next();
            int score = evalutor.evaluate(nextObj);
            if (score > this.bestScore){
                this.bestObj = nextObj;
                this.bestScore = score;
            }
        }
    }

    @Override
    public String getResult() {
        return Long.toBinaryString(this.bestObj);
    }
}
