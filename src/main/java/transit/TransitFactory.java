package transit;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.LongStream;

public class TransitFactory {

    public static Iterator<Long> createLongIterator(TransitType type, Optional<String> initValue, int bitNumber){
        long maxValue = (long) Math.pow(2,bitNumber);
        long startValue = Long.parseLong(initValue.orElse("0"),2);
        switch (type){
            case INCREASE:
                LongStream one = LongStream.range(0,maxValue);
                return one.iterator();
            case RANDOM_NEIGHBOR:
                return new RandomNeighborTransit(startValue,bitNumber);
        }
        return null;
    }

}
