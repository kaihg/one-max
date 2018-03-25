package evaluator;

public interface EvaluateFunction<T> {

    int evaluate(T item);

    int maxScore(T item);
}
