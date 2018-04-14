package evaluator;

import vo.Product;

public class ProductEvaluator implements EvaluateFunction<int[]> {

    private double maxWeight;
    private Product[] allProducts;

    public ProductEvaluator(double maxWeight, Product[] allProducts) {
        this.maxWeight = maxWeight;
        this.allProducts = allProducts;
    }

    @Override
    public int evaluate(int[] items) {
        double sumWeight = 0;
        double sumValue = 0;

        for (int i = 0; i < items.length; i++) {
            sumWeight += items[i] * allProducts[i].weight;
            sumValue += items[i] * allProducts[i].value;
        }

        return sumWeight > maxWeight ? 0 : (int) sumValue;
    }

    @Override
    public int maxScore(int[] items) {
        double sumValue = 0;

        for (int i = 0; i < items.length; i++) {
            sumValue += allProducts[i].value;
        }
        return (int) sumValue;
    }
}
