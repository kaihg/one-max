package evaluator;

import java.util.stream.IntStream;

public class ArrayCountEvaluator implements EvaluateFunction<IntStream> {
    @Override
    public int evaluate(IntStream item) {
        return (int) item.filter((x) -> x == 1).count();
    }

}
