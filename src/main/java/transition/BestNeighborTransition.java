package transition;

import evaluator.EvaluateFunction;

import java.util.Arrays;

public class BestNeighborTransition implements LongTransition<int[]> {

    private LongTransition<int[]> transition;
    private int maxCompareCount = 1;
    private EvaluateFunction<int[]> evaluateFunction;

    public BestNeighborTransition(LongTransition<int[]> transition, int maxCompareCount, EvaluateFunction<int[]> evaluateFunction) {
        this.transition = transition;
        this.maxCompareCount = maxCompareCount;
        this.evaluateFunction = evaluateFunction;
    }

    @Override
    public void update(int[] obj) {

    }

    @Override
    public void setDefaultValue(String value) {
        this.transition.setDefaultValue(value);
    }

    @Override
    public boolean hasNext() {
        return this.transition.hasNext();
    }

    @Override
    public void neighbor(int[] current, int[] empty) {
        int[] bestOne = new int[current.length];
        this.transition.neighbor(current, bestOne);
        int count = 1;
        while (count < maxCompareCount) {
            this.transition.neighbor(current, empty);
            if (evaluateFunction.evaluate(bestOne) < evaluateFunction.evaluate(empty)) {
                bestOne = Arrays.copyOf(empty, empty.length);
            }
            count++;
        }

        System.arraycopy(bestOne, 0, empty, 0, empty.length);
    }

    @Override
    public int[] next() {
        return this.transition.next();
    }
}
