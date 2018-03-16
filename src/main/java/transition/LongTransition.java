package transition;

public interface LongTransition {

    void setDefaultValue(String value);

    boolean hasNext();

    void neighbor(int[] current,int[] empty);
    int[] next();

    static void convertLongToIntAry(int bitCount,long value, int[] saveAry){
        for (int i = 0; i < bitCount; i++) {
            saveAry[i] = (int) ((value >> i) & 1);
        }
    }
}
