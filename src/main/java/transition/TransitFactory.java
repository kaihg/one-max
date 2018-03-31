package transition;

import java.util.Optional;

public class TransitFactory {

    public static LongTransition createLongIterator(TransitType type, Optional<String> initValue, int bitNumber) {
        long startValue = Long.parseLong(initValue.orElse("0"),2);
        switch (type){
            case INCREASE:
                return new IncreaseTransition(startValue, bitNumber);
            case RANDOM_NEIGHBOR:
                return new RandomNeighborTransition(startValue, bitNumber);
            case LAST_REJECT:
                return new MemoryTransition(startValue, bitNumber);
        }
        return null;
    }

    public static LongTransition<int[][]> createGeneticTransition(double crossoverRate, double mutationRate) {
        return new GeneticTransition(crossoverRate, mutationRate);
    }

}
