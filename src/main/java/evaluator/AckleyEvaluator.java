package evaluator;

public class AckleyEvaluator implements EvaluateFunction<double[]> {

    // TODO: the offset of Ackley

    private static final double PARAM_A = 20;
    private static final double PARAM_B = 0.2;
    private static final double PARAM_C = 2 * Math.PI;

    @Override
    public double evaluate(double[] item) {
        double square = 0;
        double cosSum = 0;

        for (double dim : item) {
            square += dim * dim;
            cosSum += PARAM_C * dim;
        }

        int length = item.length;

        return -PARAM_A * Math.exp(-PARAM_B * Math.sqrt(square / length)) - Math.exp(cosSum / length) + PARAM_A + Math.exp(1);
    }

    @Override
    public int maxScore(double[] item) {
        return 0;
    }
}
