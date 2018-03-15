package evaluator;

class BitCountEvaluator implements EvaluateFunction<Long> {

    @Override
    public int evaluate(Long item) {
        return Long.bitCount(item);
    }


}
