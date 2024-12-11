package org.example;

import org.example.algorithm.GeneticAlgorithm;
import org.example.algorithm.GreedyAlgorithm;
import org.example.algorithm.RandomAlgorithm;
import org.example.algorithm.SimmulatedAnnealing;
import org.example.enity.*;
import org.example.factory.CandidatePathsLoader;
import org.example.factory.ObjectFactory;
import org.example.factory.TopologyLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.enity.ModulationCalculator.calculateSlotsAndTransceivers;
import static org.example.factory.DemandLoader.loadDemandFromFile;

public class Main {
    public static void main(String[] args) {
//
////        RandomAlgorithm randomAlgorithm = new RandomAlgorithm();
////        randomAlgorithm.randomAlgorithm("src/main/resources/20to200/random.csv", 7_000_0);
//
////        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm();
////        greedyAlgorithm.greedyAlgorithm("src/main/resources/20to200/greedy.csv");
//
//        SimmulatedAnnealing simmulatedAnnealing = new SimmulatedAnnealing(160_000, 0.99);
////        simmulatedAnnealing.simulatedAnnealingAlgorithm("src/main/resources/20to200/simmulatedV1.csv");
////        simmulatedAnnealing.simulatedAnnealingAlgorithmV2("src/main/resources/20to200/simmulated.csv");
//
//        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
////        geneticAlgorithm.geneticAlgorithm(100, 10000, 70,10,5, "src/main/resources/20to200/comarasionV2.csv");
//        geneticAlgorithm.geneticAlgorithmV2(100, 4_0000, 70,10,5, "src/main/resources/20to200/comparasion.csv");

        String filePath = "src/main/resources/demands/demands_0/";
        List<Demand> demands = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            try {
                Demand demand = loadDemandFromFile(filePath + i + ".txt");
                demands.add(demand);
                System.out.println(demand);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(demands);

        Topology topology = TopologyLoader.readFromFile("src/main/resources/topology/pol12.net");
        topology.printDistanceMatrix();

        CandidatePaths candidatePaths = new CandidatePaths();
        candidatePaths = CandidatePathsLoader.loadCandidatePathsFromFile("src/main/resources/routing/pol12.pat");

        IndividualV2 individualV2= new IndividualV2(demands,topology,candidatePaths);
//        candidatePaths.printPaths();
        individualV2.generateFirsFit();

        System.out.println(Modulation.getModulationForDistanceAndBitrate(2000,800.0));
        System.out.println(Modulation.getModulationForDistanceAndBitrate(2000,15643.0));
        System.out.println(Modulation.getModulationForDistanceAndBitrate(2000,800.0));

        ModulationCalculator.Result result = calculateSlotsAndTransceivers(2000,800.0);
        ModulationCalculator.Result result2 = calculateSlotsAndTransceivers(2000,15643.0);
        System.out.println(result);
        System.out.println(result2);

        int distance = 5000; // in km
        int bitrate = 900;  // in Gbps



    }

}