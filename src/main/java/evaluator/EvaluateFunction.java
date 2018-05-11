package evaluator;

public interface EvaluateFunction<T> {

    double evaluate(T item);

    int maxScore(T item);
}
