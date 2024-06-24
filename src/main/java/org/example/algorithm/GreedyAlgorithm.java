package org.example.algorithm;

import com.opencsv.CSVWriter;
import org.example.enity.Individual;
import org.example.factory.ObjectFactory;

import java.io.FileWriter;
import java.io.IOException;

public class GreedyAlgorithm {
    public void greedyAlgorithm(String fileToSave){
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(fileToSave))){
            String[] headers = {"Iteracja", "Current", "Best", "Worst", "Average"};
            csvWriter.writeNext(headers);
            var bestResult = Float.MIN_VALUE;
            var worstResult = Float.MAX_VALUE;
            long average = 0;
            Individual individual = generateIndividual();
            var currentCost = individual.getCost();
            if (currentCost < bestResult) bestResult = currentCost;
            if (currentCost > worstResult) worstResult = currentCost;
            average+= currentCost;
            String[] row = {String.valueOf(1), String.valueOf(currentCost), String.valueOf(bestResult), String.valueOf(worstResult), String.valueOf(average/(1))};
            csvWriter.writeNext(row);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Individual generateIndividual() throws IOException {

        String filePath = "src/main/resources/data.json";
        ObjectFactory objectFactory = new ObjectFactory();
//        Individual individual = objectFactory.createIndividualFromJson(filePath);
//        Individual individual = objectFactory.loadIndividualFromFile("providers_data.json");
        Individual individual = ObjectFactory.getIndividual();
        System.out.println(individual);
        individual.generateGreedySequenceOfProviders();
        individual.createBasket();
        System.out.println("Greedy provider with maxOccurences" + individual.getMaxOccurences());
        System.out.println("Greedy size of providers " + individual.getUniqueNumberCounter());
        System.out.println(individual.getBasket());
        return individual;
    }
}
