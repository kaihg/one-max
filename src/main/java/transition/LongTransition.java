package transition;

public interface LongTransition<T> {

    void update(T obj);

    void setDefaultValue(String value);

    boolean hasNext();

    void neighbor(T current, T empty);

    T next();

    static void convertLongToIntAry(int bitCount,long value, int[] saveAry){
        for (int i = 0; i < bitCount; i++) {
            saveAry[i] = (int) ((value >> i) & 1);
        }
    }
}
