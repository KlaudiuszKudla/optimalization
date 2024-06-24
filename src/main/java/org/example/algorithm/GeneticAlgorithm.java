package org.example.algorithm;

import com.opencsv.CSVWriter;
import org.example.enity.Individual;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GeneticAlgorithm extends Algorithm {


    public void geneticAlgorithm(int popSize, int generations, int crossProbability, int mutationProbability, int tourSize, String fileToSave) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileToSave))) {
            String[] headers = {"Iteracja", "Best Result", "Worst Result", "Average Cost"};
            writer.writeNext(headers);
            generateRandomIndividuals(popSize);
            Individual currentBestIndividual = null;
            for (int i = 0; i < generations; i++) {
                List<Individual> tournamentWinners = findTournamentWinners(population, popSize/10, tourSize);
                crossTournamentWinners(tournamentWinners, crossProbability, population, mutationProbability);
                if (i % 10 == 0) {
                    mutatePopulation(population, mutationProbability);
                }
                currentBestIndividual = findBestIndividual(population);
                var worstResult = findWorstIndividual(population).getCost();
                var averageCost = getAverageCost(population);
                String[] row = {String.valueOf(i + 1), String.valueOf(currentBestIndividual.getCost()), String.valueOf(worstResult), String.valueOf(averageCost)};
                writer.writeNext(row);
                currentBestIndividual.createBasket();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void geneticAlgorithmV2(int popSize, int ffe, int crossProbability, int mutationProbability, int tourSize, String fileToSave) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileToSave))) {
            String[] headers = {"Iteracja", "Best Result", "Worst Result", "Average Cost"};
            writer.writeNext(headers);
            for (int i = 0; i < 10; i++) {
                generateRandomIndividuals(popSize);
                Individual currentBestIndividual = null;
                int currentFfe = 0;
                while (currentFfe < ffe) {
                    List<Individual> tournamentWinners = findTournamentWinners(population, popSize/10, tourSize);
                    currentFfe += crossTournamentWinners(tournamentWinners, crossProbability, population, mutationProbability);
//                    if (currentFfe % 10 == 0) {
//                        mutatePopulation(population, mutationProbability);
//                        currentFfe += population.size();
//                    }
                }
                currentBestIndividual = findBestIndividual(population);
                var worstResult = findWorstIndividual(population).getCost();
                var averageCost = getAverageCost(population);
                System.out.println(worstResult);
                System.out.println(currentBestIndividual.getCost());
                String[] row = {String.valueOf(i + 1), String.valueOf(currentBestIndividual.getCost()), String.valueOf(worstResult), String.valueOf(averageCost)};
                writer.writeNext(row);
                System.out.println("po pierwszym");
                population.clear();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void geneticAlgorithmV3(int popSize, int generations, int crossProbability, int mutationProbability, int tourSize, String fileToSave) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileToSave))) {
            String[] headers = {"Iteracja", "Best Result", "Worst Result", "Average Cost"};
            writer.writeNext(headers);
            for (int i = 0; i < 10; i++) {
                generateRandomIndividuals(popSize);
                Individual currentBestIndividual = null;
                int currentFfe = 0;
                for (int j = 0; j < generations; j++) {
                    List<Individual> tournamentWinners = findTournamentWinners(population, popSize/10, tourSize);
                    currentFfe += crossTournamentWinners(tournamentWinners, crossProbability, population, mutationProbability);
                    if (generations % 10 == 0) {
                        mutatePopulation(population, mutationProbability);
                        currentFfe += population.size();
                    }
                }
                currentBestIndividual = findBestIndividual(population);
                var worstResult = findWorstIndividual(population).getCost();
                var averageCost = getAverageCost(population);
                System.out.println(worstResult);
                System.out.println(currentBestIndividual.getCost());
                String[] row = {String.valueOf(i + 1), String.valueOf(currentBestIndividual.getCost()), String.valueOf(worstResult), String.valueOf(averageCost)};
                writer.writeNext(row);
                System.out.println("po pierwszym");
                population.clear();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Individual> findTournamentWinners(List<Individual> population, int times, int tourSize) {
        Set<Individual> bestResults = new HashSet<>();
        while (bestResults.size() < times) {
            List<Individual> tournamentParticipants = shuffleIndividuals(population, tourSize);
            var bestResult = findBestIndividual(tournamentParticipants);
            bestResults.add(bestResult);
        }
        return new ArrayList<>(bestResults);
    }

    private int crossTournamentWinners(List<Individual> tournamentWinners, int crossProbability, List<Individual> population, int mutationProbability) {
        var sizeOfTournamentWinners = tournamentWinners.size();
        Random random = new Random();
        List<Individual> newPopulation = new ArrayList<>();
        int ffeCounter = 0;
//
//         Elityzm: Dodanie najlepszych 10% osobników do nowej populacji
        int elitismCount = (int) Math.ceil(population.size() * 0.1);
        List<Individual> elites = findBestIndividuals(population, elitismCount);
        newPopulation.addAll(elites);

        // Tworzenie nowej populacji poprzez krzyżowanie
        while (newPopulation.size() < population.size()) {
            var randomIndex = random.nextInt(sizeOfTournamentWinners);
            var secondRandomIndex = random.nextInt(sizeOfTournamentWinners);
            Individual parent1 = tournamentWinners.get(randomIndex);
            while (randomIndex == secondRandomIndex) {
                secondRandomIndex = random.nextInt(sizeOfTournamentWinners);
            }
            Individual parent2 = tournamentWinners.get(secondRandomIndex);
            if (shouldCross(crossProbability)) {
                Individual child1 = orderedCrossover(parent1, parent2);
                Individual child2 = orderedCrossover(parent2, parent1);
                ffeCounter += 2;
                if (shouldMutate(mutationProbability)) {
                    child1 = child1.inversionMutation();
                    child2 = child2.inversionMutation();
                    ffeCounter += 2;
                }
                newPopulation.add(child1);
                newPopulation.add(child2);
            } else {
                newPopulation.add(parent1);
                newPopulation.add(parent2);
            }
        }

        // Upewnienie się, że populacja nie przekracza pożądanego rozmiaru
        if (newPopulation.size() > population.size()) {
            newPopulation = newPopulation.subList(0, population.size());
        }

        population.clear();
        population.addAll(newPopulation);
        return ffeCounter;
    }

    private List<Individual> findBestIndividuals(List<Individual> population, int count) {
        return population.stream()
                .sorted(Comparator.comparing(Individual::getCost))
                .limit(count)
                .toList();
    }

    public Individual orderedCrossover(Individual unit1, Individual unit2) {
        java.util.Random rand = new Random();
        var sequenceOfProviders = unit1.getSequenceOfProviders();
        var sequenceOfProviders1 = unit2.getSequenceOfProviders();

        var newSequenceOfProviders = new ArrayList<Integer>(sequenceOfProviders);
        var size = sequenceOfProviders.size();
        var firstcut = rand.nextInt(size);
        var secondCut = rand.nextInt(firstcut, size);
        if (secondCut < size - 1) {
            for (int i = secondCut + 1; i < size; i++) {
                var provider = sequenceOfProviders1.get(i);
                newSequenceOfProviders.set(i, provider);
            }
        }
        if (firstcut > 0) {
            for (int i = 0; i < firstcut; i++) {
                var provider = sequenceOfProviders1.get(i);
                newSequenceOfProviders.set(i, provider);
            }
        }
        Individual newIdividual = new Individual(newSequenceOfProviders);
        newIdividual.repairSolutionV2();
        newIdividual.calculateCost();
        return newIdividual;
    }

    public void mutatePopulation(List<Individual> population, int mutationProbability) {
        population.stream()
                .filter(individual -> shouldMutate(mutationProbability))
                .forEach(individual -> individual.swapMutationV2());
    }

    private boolean shouldCross(int crossProbability) {
        java.util.Random rand = new java.util.Random();
        int randomValue = rand.nextInt(100);
        return randomValue < crossProbability;
    }

    private boolean shouldMutate(int mutationProbability) {
        java.util.Random rand = new Random();
        int randomValue = rand.nextInt(100);
        return randomValue < mutationProbability;
    }
}
