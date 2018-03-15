package transition;

class RandomNeighborTransition implements LongTransition {

    private long current;
    private int bitNumber;

    public RandomNeighborTransition(long start, int bitNumber) {
        this.current = start;
        this.bitNumber = bitNumber;
    }


    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public int[] next() {
        return new int[0];
    }

//    @Override
//    public long next() {
//        int bit = new Random().nextInt(bitNumber);
//        long temp = current;
//        current = current ^ (0b1 << bit);
//
//        return temp;
//    }
}
