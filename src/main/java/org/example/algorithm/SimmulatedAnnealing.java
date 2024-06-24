package org.example.algorithm;

import com.opencsv.CSVWriter;
import org.example.enity.Individual;
import org.example.factory.ObjectFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimmulatedAnnealing {

    private Individual individual;
    private Float cost;
    private double temperature;
    private double coolingRate;

    public SimmulatedAnnealing(double temperature, double coolingRate) {
        Individual individual1 = ObjectFactory.getIndividual();
        individual1.generateRandomSolution();
        this.individual = individual1;
        this.cost = individual1.getCost();
        this.temperature = temperature;
        this.coolingRate = coolingRate;

    }

    public void simulatedAnnealingAlgorithm(String fileToSave) {
        var iteration = 0;
        // 1. find neighbours and choose bestOne then do the same with best one - inversion, swap, insert????
        try (CSVWriter writer = new CSVWriter(new BufferedWriter(new FileWriter(fileToSave)))) {
            String[] headers = {"Iteracja", "CurrentCost", "Best Result", "Worst Result", "Average Cost"};
            writer.writeNext(headers);
            var bestResult = Float.MAX_VALUE;
            var worstResult = Float.MIN_VALUE;
            long sum = 0;
            List<Float> costs = new ArrayList<>();
            long average = 0;
            for (int i = 0; i < 1_000_000; i++) {
                Individual neighbour = this.individual.inversionMutation();
                var neighbourCost = neighbour.getCost();
                var individualCost = individual.getCost();
                if (neighbourCost < individualCost || acceptWorseResult(neighbourCost, individualCost)) {
                    individual = neighbour;
                }
                temperature *= coolingRate;
                var currentCost = individual.getCost();
                if (currentCost < bestResult) bestResult = currentCost;
                if (currentCost > worstResult) worstResult = currentCost;
                sum += currentCost;
                costs.add(currentCost);
                average += currentCost;
                String[] row = {String.valueOf(i + 1), String.valueOf(currentCost), String.valueOf(bestResult), String.valueOf(worstResult), String.valueOf(average / (i + 1))};
                writer.writeNext(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void simulatedAnnealingAlgorithmV2(String fileToSave) {
        try (CSVWriter writer = new CSVWriter(new BufferedWriter(new FileWriter(fileToSave)))) {
            String[] headers = {"Iteracja", "Best Result", "Worst Result", "Average Cost","Iteration Time (ms)", "OdchylenieStandardowe"};
            writer.writeNext(headers);
            Float bestResult = Float.MAX_VALUE;
            Float worstResult = Float.MIN_VALUE;

            for (int j = 0; j <10; j++) {
                long iterationStartTime = System.nanoTime();
                long sum = 0;
                List<Float> costs = new ArrayList<>();
                for (int i = 0; i < 7_000_00; i++) {
                    Individual neighbour = this.individual.inversionMutation();
                    Float neighbourCost = neighbour.getCost();
                    Float individualCost = individual.getCost();
                    if (neighbourCost < individualCost || acceptWorseResult(neighbourCost, individualCost)) {
                        individual = neighbour;
                    }
                    temperature *= coolingRate;
                    var currentCost = individual.getCost();
                    if (currentCost < bestResult) bestResult = currentCost;
                    if (currentCost > worstResult) worstResult = currentCost;
                    sum += currentCost;
                    costs.add(currentCost);
                }
                long iterationEndTime = System.nanoTime();
                long iterationTime = TimeUnit.NANOSECONDS.toMillis(iterationEndTime - iterationStartTime);

                // Obliczenie średniej z kosztów bieżącej iteracji
                long average = sum / 10_000;
                String[] row = {String.valueOf(j + 1),
                        String.valueOf(bestResult),
                        String.valueOf(worstResult),
                        String.valueOf(average),
                        String.valueOf(iterationTime)};
                writer.writeNext(row);
                Individual individual1 = ObjectFactory.getIndividual();
                individual1.generateRandomSolution();
                this.individual = individual1;
                this.cost = individual1.getCost();
                costs.clear();
                // Resetowanie zmiennych przed kolejną iteracją
                bestResult = Float.MAX_VALUE;
                worstResult = Float.MIN_VALUE;
                this.cost = individual.getCost();
                temperature = 0.99;
                coolingRate = 0.99;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean acceptWorseResult(Float neighbourCost, Float individualCost) {
        double probability = Math.exp((individualCost - neighbourCost) / temperature);
        return Math.random() < probability;
    }

}
