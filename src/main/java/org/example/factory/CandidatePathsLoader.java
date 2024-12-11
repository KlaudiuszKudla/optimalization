package org.example.factory;

import org.example.enity.CandidatePaths;
import org.example.enity.Demand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandidatePathsLoader {

    // Statyczna metoda parsująca dane wejściowe
    public static CandidatePaths loadCandidatePathsFromFile(String filePath) {
//        String[] lines = input.trim().split("\n");
//        int numberOfPaths = Integer.parseInt(lines[0]); // Pierwsza linia: liczba ścieżek
//
//        List<List<Integer>> paths = new ArrayList<>();
//
//        for (int i = 1; i < lines.length; i++) {
//            String[] values = lines[i].trim().split("\\s+"); // Podziel po spacji
//            List<Integer> path = new ArrayList<>();
//            for (String value : values) {
//                path.add(Integer.parseInt(value));
//            }
//            paths.add(path);
//        }


        CandidatePaths candidatePaths =  new CandidatePaths();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ;
            // Czytanie węzłów startowego i końcowego
            candidatePaths.setNumberOfPaths(Integer.parseInt(reader.readLine().trim()));
            List<List<Integer>> paths = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                List<Integer> path = new ArrayList<>();
                for (String value : values) {
                    path.add(Integer.parseInt(value));
                }
                paths.add(path);
            }
            candidatePaths.setPaths(paths);
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
        }
        return candidatePaths;
    }

}
