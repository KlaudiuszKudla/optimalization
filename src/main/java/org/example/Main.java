package org.example;

import org.example.algorithm.GeneticAlgorithm;
import org.example.algorithm.GreedyAlgorithm;
import org.example.algorithm.RandomAlgorithm;
import org.example.algorithm.SimmulatedAnnealing;
import org.example.enity.Individual;
import org.example.enity.Product;
import org.example.enity.Provider;
import org.example.factory.ObjectFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        RandomAlgorithm randomAlgorithm = new RandomAlgorithm();
//        randomAlgorithm.randomAlgorithm("src/main/resources/20to200/random.csv", 7_000_0);

//        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm();
//        greedyAlgorithm.greedyAlgorithm("src/main/resources/20to200/greedy.csv");

        SimmulatedAnnealing simmulatedAnnealing = new SimmulatedAnnealing(160_000, 0.99);
//        simmulatedAnnealing.simulatedAnnealingAlgorithm("src/main/resources/20to200/simmulatedV1.csv");
//        simmulatedAnnealing.simulatedAnnealingAlgorithmV2("src/main/resources/20to200/simmulated.csv");

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
//        geneticAlgorithm.geneticAlgorithm(100, 10000, 70,10,5, "src/main/resources/20to200/comarasionV2.csv");
        geneticAlgorithm.geneticAlgorithmV2(100, 4_0000, 70,10,5, "src/main/resources/20to200/comparasion.csv");
    }

}