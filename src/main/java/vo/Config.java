package vo;

public class Config {
    public String[] compareAlgorithms;
    public Product[] items;
    public double maxWeight;
    public int iteration;
    public int runTimes;
    public boolean useRandomItem;
    public RangeSetting randomRange;

    public HillClimbingParam hillClimbingParam;
    public SimulateAnnealingParam simulateAnnealingParam;
    public TabuSearchParam tabuSearchParam;
    public GeneticParam geneticParam;

}
