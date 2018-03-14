package transit;

import java.util.Iterator;
import java.util.Random;

class RandomNeighborTransit implements Iterator<Long> {

    private long current;
    private int bitNumber;

    public RandomNeighborTransit(long start, int bitNumber){
        this.current = start;
        this.bitNumber = bitNumber;
    }


    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Long next() {
        int bit = new Random().nextInt(bitNumber);
        long temp = current;
        current = current ^ (0b1 << bit);

        return temp;
    }
}
