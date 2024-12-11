package org.example.factory;

import org.example.enity.Demand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemandLoader {

    public static Demand loadDemandFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Demand demand = new Demand();
        try {
            // Czytanie węzłów startowego i końcowego
            demand.setStartingNode(Integer.parseInt(reader.readLine().split(" ")[0]));
            demand.setEndingNode(Integer.parseInt(reader.readLine().split(" ")[0]));
            reader.readLine(); // skip 3rd row

            String line = reader.readLine().trim();

            // Czytanie bitrate'ów
            List<Double> bitrates = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                bitrates.add(Double.parseDouble(line.trim()));
            }
            demand.setBitrates(bitrates);

        } finally {
            reader.close();
        }
        return demand;
    }
}