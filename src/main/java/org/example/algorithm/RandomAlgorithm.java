package org.example.algorithm;

import com.opencsv.CSVWriter;
import org.example.enity.Individual;
import org.example.factory.ObjectFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomAlgorithm {

    public void randomAlgorithm(String fileToSave, int numOperations){
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(fileToSave))){
            String[] headers = {"Iteracja", "Current", "Best", "Worst", "Average"};
            csvWriter.writeNext(headers);
            Float bestResult = Float.MAX_VALUE;
            Float worstResult = Float.MIN_VALUE;
            long sum = 0;
            List<Float> costs = new ArrayList<>();
            long average = 0;
            for (int i = 0; i < numOperations; i++) {
                Individual individual = ObjectFactory.getIndividual();
                individual.generateRandomSolution();
                var currentCost = individual.getCost();
                if (currentCost < bestResult) bestResult = currentCost;
                if (currentCost > worstResult) worstResult = currentCost;
                sum += currentCost;
                costs.add(currentCost);
                average+= currentCost;
                double std = calculateStd(costs, average / (double) (i + 1));
                String[] row = {String.valueOf(i + 1), String.valueOf(currentCost), String.valueOf(bestResult),
                        String.valueOf(worstResult), String.valueOf(average / (i + 1)), String.valueOf(std)};
                csvWriter.writeNext(row);
                individual.sequenceOfProviders.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculateStd(List<Float> costs, double mean) {
        double sum = 0.0;
        for (Float cost : costs) {
            sum += Math.pow(cost - mean, 2);
        }
        return Math.sqrt(sum / costs.size());
    }
}
