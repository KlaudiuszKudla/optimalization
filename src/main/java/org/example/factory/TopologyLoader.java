package org.example.factory;

import org.example.enity.Topology;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TopologyLoader {

    public static Topology readFromFile(String filename) {
        Topology topology = new Topology();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Wczytywanie liczby węzłów

            int numberOfNodes = Integer.parseInt(br.readLine().trim());
            topology.setNumberOfNodes(numberOfNodes);

            topology.setNumberOfEdges(Integer.parseInt(br.readLine().trim()));

            // Inicjalizacja macierzy odległości
            int[][] distanceMatrix = new int[numberOfNodes][numberOfNodes];

            // Wczytywanie danych do macierzy
            for (int i = 0; i < numberOfNodes; i++) {
                String[] line = br.readLine().trim().split("\t");
                for (int j = 0; j < numberOfNodes; j++) {
                    distanceMatrix[i][j] = Integer.parseInt(line[j]);
                }
            }
            topology.setDistanceMatrix(distanceMatrix);
            topology.setEdges();


        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
        }
        return topology;
    }
}
