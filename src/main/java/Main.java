import model.AlgorithmModel;
import model.ModelFactory;

public class Main {
    public static void main(String[] args) {
        int testN = 20; // default
        String algorithm = ModelFactory.EXHAUSTION_SEARCH;
        if (args.length > 0) {
            try {
                algorithm = args[0];
                testN = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        AlgorithmModel model = ModelFactory.createModel(algorithm, testN);
        model.start();
        System.out.println(String.format("The one-max result of 2^%d is %s, it's score is %d", testN, model.getResult(), model.getScore()));
    }


    private long bestScore = -1;
    private long bestObj;


    void findAllObj(int n) {
        long maxValue = (long) Math.pow(2, n);

//        LongStream one = LongStream.iterate(0, x -> x + 1).limit(maxValue);
//        one.forEach(this::checkBestObj);

//        LongStream one = LongStream.range(0,maxValue);
//        one.forEach(this::checkBestObj);

//        LongStream one = LongStream.range(0,maxValue);
//        Iterator<Long> iterator = one.iterator();
//        while (iterator.hasNext()){
//            checkBestObj(iterator.next());
//        }

        long count = 0;
        while (count < maxValue){
            checkBestObj(count);
            count ++ ;
        }

//        for (long i = 0; i < maxValue; i++) {
//            checkBestObj(i);
//        }

    }


    private void checkBestObj(long value) {
        long score = computerScore(value);
        if (score > bestScore) {
            bestScore = score;
            bestObj = value;
        }
    }

    int computerScore(long value) {
        return Long.bitCount(value);
//        int count = 0;
//
//        while (value > 0) {
//            count += value & 0b1;
//            value = value >> 1;
//        }
//
//        return count;
    }

    public long getBestScore() {
        return bestScore;
    }

    public String getBestObj() {
        return Long.toBinaryString(bestObj);
    }
}
