package evaluator;

import vo.Product;

import java.util.Arrays;

public class EvaluatorFactory {

    public static EvaluateFunction createLongEvaluator() {
        // Cause it is a practice project, return the manual evaluator
        return new IntArrayCountEvaluator();
    }

    public static EvaluateFunction createTKPEvaluator(double... params) {
        return new ProductEvaluator(params[0], createProducts(Arrays.copyOfRange(params, 1, params.length)));
    }

    public static EvaluateFunction createTKPEvaluator(double maxWeight, Product[] params) {
        return new ProductEvaluator(maxWeight, params);
    }

    public static EvaluateFunction createAckleyEvaluator() {
        return new AckleyEvaluator();
    }

    public static EvaluateFunction createEulcideanEvaluator() {
        return new EuclideanEvaluator();
    }

    private static Product[] createProducts(double... weight_values) {
        int length = weight_values.length / 2;
        Product[] items = new Product[length];

        for (int i = 0; i < length; i++) {
            double weight = weight_values[i * 2];
            double value = weight_values[i * 2 + 1];
            items[i] = new Product(weight, value);
        }

        return items;
    }

}
