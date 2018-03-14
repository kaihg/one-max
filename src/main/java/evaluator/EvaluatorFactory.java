package evaluator;

public class EvaluatorFactory {

    public static EvaluateFunction<Long> createLongEvaluator(){
        // Cause it is a practice project, return the manual evaluator
        return new ManualBitCountEvaluator();
    }

}
