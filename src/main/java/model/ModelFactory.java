package model;

import com.google.gson.Gson;
import evaluator.EuclideanEvaluator;
import evaluator.EvaluateFunction;
import evaluator.EvaluatorFactory;
import transition.BestNeighborTransition;
import transition.LongTransition;
import transition.TransitFactory;
import transition.TransitType;
import vo.Config;
import vo.Product;
import vo.RangeSetting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class ModelFactory {

    public static final String EXHAUSTION_SEARCH = "es";
    public static final String HILL_CLIMBING = "hc";
    public static final String SIMULATED_ANNEALING = "sa";
    public static final String TABU_SEARCH = "ts";
    public static final String GENETIC_ALGORITHM = "ga";
    public static final String GENETIC_ALGORITHM_TKP = "ga_tkp";
    public static final String TKP_COMPARE = "tkp";
    public static final String PSO_ALGORITHM = "pso";
    public static final String GENETIC_ALGORITHM_KMEAN = "ga_kmean";

    public static AlgorithmModel createModel(String algorithm, int bitCount, int runTimes, int iterationCount, int neighborPickCount, double... extraParams) {
        AlgorithmModel model = null;
        LongTransition transition = null;
        EvaluateFunction function = null;
        switch (algorithm) {
            case EXHAUSTION_SEARCH:
                model = new ExhaustionModel();
                transition = TransitFactory.createLongIterator(TransitType.INCREASE, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case HILL_CLIMBING:
            default:
                model = new HillClimbingModel(bitCount, iterationCount);
                transition = TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case SIMULATED_ANNEALING:
                double temperature = extraParams.length < 1 ? bitCount : extraParams[0];
                model = new SimulatedAnnealingModel(bitCount, iterationCount, temperature);
                transition = TransitFactory.createLongIterator(TransitType.RANDOM_NEIGHBOR, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case TABU_SEARCH:
                model = new TabuModel(bitCount, iterationCount);
                transition = TransitFactory.createLongIterator(TransitType.LAST_REJECT, Optional.of("0"), bitCount);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case GENETIC_ALGORITHM:
                model = new GeneticModel(iterationCount, (int) extraParams[0], bitCount);
                transition = TransitFactory.createGeneticTransition(extraParams[1], extraParams[2]);
                function = EvaluatorFactory.createLongEvaluator();
                break;
            case GENETIC_ALGORITHM_TKP:
                model = new GeneticModel(iterationCount, (int) extraParams[0], bitCount);
                transition = TransitFactory.createGeneticTransition(extraParams[1], extraParams[2]);
                function = EvaluatorFactory.createTKPEvaluator(Arrays.copyOfRange(extraParams, 3, extraParams.length));
                break;

        }

        if (neighborPickCount > 1) {
            transition = new BestNeighborTransition(transition, neighborPickCount, function);
        }

        if (runTimes > 1) {
            AlgorithmModel[] models = new AlgorithmModel[runTimes];
            for (int i = 0; i < runTimes; i++) {
                models[i] = createModel(algorithm, bitCount, 1, iterationCount, neighborPickCount, extraParams);
            }
            model = new RepeatModel(models, runTimes);
            return model;
        }


        model.setEvaluator(function);
        model.setTransit(transition);

        return model;
    }

    public static CompareModel createTKPCompareModels(String paramPath) throws FileNotFoundException {

        Config config = parseJsonFile(paramPath);

        EvaluateFunction function = EvaluatorFactory.createTKPEvaluator(config.maxWeight, config.items);
        AlgorithmModel[] models = new AlgorithmModel[config.compareAlgorithms.length];

        int bitCount = config.items.length;
        for (int i = 0; i < config.compareAlgorithms.length; i++) {
            String name = config.compareAlgorithms[i];
            models[i] = createModel(name, bitCount, config.iteration, config);
            models[i].setEvaluator(function);
        }

        CompareModel compareModel = new CompareModel(models, config.iteration, config.compareAlgorithms);

        return compareModel;
    }

    public static AlgorithmModel createModel(String algorithm, String paramPath) throws FileNotFoundException {
        Config config = parseJsonFile(paramPath);
        AlgorithmModel model = createModel(algorithm, config.items.length, config.iteration, config);
        return model;
    }

    private static AlgorithmModel createModel(String algorithm, int bitCount, int iteration, Config config) {
        switch (algorithm) {
            case HILL_CLIMBING:
                return createModel(algorithm, bitCount, config.runTimes, iteration, config.hillClimbingParam.neighbor);
            case SIMULATED_ANNEALING:
                return createModel(algorithm, bitCount, config.runTimes, iteration, config.simulateAnnealingParam.neighbor, config.simulateAnnealingParam.temperature);
            case TABU_SEARCH:
                return createModel(algorithm, bitCount, config.runTimes, iteration, config.tabuSearchParam.neighbor);
            case GENETIC_ALGORITHM:
                return createModel(algorithm, bitCount, config.runTimes, iteration, 1, config.geneticParam.population, config.geneticParam.crossoverRate, config.geneticParam.mutationRate);
            case PSO_ALGORITHM:
                return new PSOModel(iteration, config.psoAckleyParam);
            case GENETIC_ALGORITHM_KMEAN:
                return createGeneticKmeanModel(iteration, config.runTimes, config);
        }
        return null;
    }

    private static AlgorithmModel createGeneticKmeanModel(int iteration, int runTimes, Config config) {
        AlgorithmModel model = new GeneticKmeanModel(iteration, config.geneticParam.population, config.k_meanParam.data, config.k_meanParam.group_k);

        LongTransition transition = TransitFactory.createGeneticTransition(config.geneticParam.crossoverRate, config.geneticParam.mutationRate);
        EvaluateFunction function = new EuclideanEvaluator();

        model.setTransit(transition);
        model.setEvaluator(function);

        return model;
    }

    private static Config parseJsonFile(String path) throws FileNotFoundException {
        System.out.println(path);
        ClassLoader classLoader = ModelFactory.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        Config config = new Gson().fromJson(new FileReader(file), Config.class);

        if (config.useRandomItem) {
            setRandomProduct(config);
        }
        return config;
    }

    private static void setRandomProduct(Config config) {
        Random random = new Random();
        ArrayList<Product> list = new ArrayList<>();

        RangeSetting setting = config.randomRange;
        // 先不使用double，只用int就好
        int minWeight = (int) setting.weight[0];
        int maxWeight = (int) setting.weight[1] - minWeight + 1;
        int minValue = (int) setting.value[0];
        int maxValue = (int) setting.value[1] - minValue + 1;

        for (int i = 0; i < setting.itemSize; i++) {
            Product product = new Product(random.nextInt(maxWeight) + minWeight, random.nextInt(maxValue) + minValue);
            list.add(product);
        }
        Product[] array = new Product[100];
        list.toArray(array);
        config.items = array;
    }

    public static String getAlgorithmName(String shortCode) {
        switch (shortCode) {
            case EXHAUSTION_SEARCH:
                return "exhaustion search";
            case HILL_CLIMBING:
                return "hill climbing search";
            case SIMULATED_ANNEALING:
                return "simulated annealing";
            case TABU_SEARCH:
                return "tabu search";
            case GENETIC_ALGORITHM:
                return "Genetic Algorithm";
            case GENETIC_ALGORITHM_TKP:
                return "Genetic Algorithm for TKP problem";
            case GENETIC_ALGORITHM_KMEAN:
                return "Genetic Algorithm for K-Means";
            case TKP_COMPARE:
                return "TKP";
            case PSO_ALGORITHM:
                return "Particle Swarm Optimization";
            default:
                return "Nothing";
        }
    }

    public static AlgorithmModel createPSOModelFromFile(String path, String algoritmName) throws FileNotFoundException {

        Config config = parseJsonFile(path);

        EvaluateFunction function = EvaluatorFactory.createAckleyEvaluator();
        LongTransition transition = TransitFactory.createSwarmTransition(config);

        AlgorithmModel model = createModel(algoritmName, config.psoAckleyParam.dimRange.length, config.iteration, config);
        model.setEvaluator(function);
        model.setTransit(transition);

        return model;
    }
}
