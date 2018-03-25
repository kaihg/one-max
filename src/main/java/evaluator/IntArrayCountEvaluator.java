package evaluator;

public class IntArrayCountEvaluator implements EvaluateFunction<int[]> {

    @Override
    public int evaluate(int[] item) {
        int count = 0;
        for (int element : item) {
            count += element;
        }
        return count;
    }

    @Override
    public int maxScore(int[] item) {
        return item.length;
    }

}
