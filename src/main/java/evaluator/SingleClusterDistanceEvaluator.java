package evaluator;

import vo.Clustering;

public class SingleClusterDistanceEvaluator implements EvaluateFunction<Clustering<double[]>> {

    @Override
    public double evaluate(Clustering<double[]> item) {
        double[] center = item.center;

        double dist = 0;
        for (double[] unit : item.unitArray) {
            double temp = 0;
            for (int i = 0; i < center.length; i++) {
                temp += Math.pow(center[i] - unit[i], 2);
            }
            dist += Math.sqrt(temp);
        }

        return dist;
    }

    @Override
    public int maxScore(Clustering<double[]> item) {
        return 0;
    }
}
