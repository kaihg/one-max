package evaluator;

class ManualBitCountEvaluator implements EvaluateFunction<Long> {

    @Override
    public int evaluate(Long value) {
        return evaluate(value.longValue());
    }

    private int evaluate(long value) {
        int count = 0;

        while (value > 0) {
            count += value & 0b1;
            value = value >> 1;
        }

        return count;
    }
}
