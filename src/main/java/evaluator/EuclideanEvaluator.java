package evaluator;

import model.EuclideanClusterModel;

public class EuclideanEvaluator implements EvaluateFunction<EuclideanClusterModel> {

    @Override
    public double evaluate(EuclideanClusterModel item) {

        double sum = 0;
        for (int i = 0; i < item.getClusterFlags().length; i++) {
            int flag = item.getClusterFlags()[i];
            double[] center = item.getCenters()[flag];

            sum += getDistance(center, item.getDatas()[i]);
        }

        return sum;
    }

    private double getDistance(double[] a, double[] b) {
        double temp = 0;
        for (int i = 0; i < a.length; i++) {
            temp += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(temp);
    }

    @Override
    public int maxScore(EuclideanClusterModel item) {
        return 0;
    }
}
