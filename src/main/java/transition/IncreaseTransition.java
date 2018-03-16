package transition;

import java.util.Arrays;

public class IncreaseTransition implements LongTransition {

    private long current;
    private long max;
    private int bitCount;
    private int[] currentAry;

    public IncreaseTransition(long start, int bitCount) {
        this.current = start;
        this.max = 1 << bitCount;
        this.bitCount = bitCount;

        currentAry = new int[bitCount];
        LongTransition.convertLongToIntAry(bitCount,start,currentAry);

    }



    @Override
    public void setDefaultValue(String value) {
        long longValue = Long.parseLong(value);
        LongTransition.convertLongToIntAry(bitCount,longValue,currentAry);
        this.current = longValue;
    }

    @Override
    public boolean hasNext() {
        return current < max;
    }

    @Override
    public void neighbor(int[] current, int[] empty) {
        Arrays.setAll(empty,(i)->current[i]);
        for (int i = 0; i < current.length; i++) {
            if (current[i] == 0) {
                empty[i] = 1;
                break;
            } else {
                empty[i] = 0;
            }
        }
    }

    @Override
    public int[] next() {
        for (int i = 0; i < currentAry.length; i++) {
//            currentAry[i] = (int) ((current >> i) & 1);
            if (currentAry[i] == 0) {
                currentAry[i] = 1;
                break;
            } else {
                currentAry[i] = 0;
            }
        }
        current++;

        return currentAry;
    }
}
