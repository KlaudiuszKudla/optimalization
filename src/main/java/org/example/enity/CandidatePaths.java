package org.example.enity;

import java.util.ArrayList;
import java.util.List;

public class CandidatePaths {

    public static int NUMBER_OF_CANDIDATE_PATHS = 30;
    private int numberOfPaths;
    private List<List<Integer>> paths;

    public int getNumberOfPaths() {
        return numberOfPaths;
    }

    public void setNumberOfPaths(int numberOfPaths) {
        this.numberOfPaths = numberOfPaths;
    }

    public List<List<Integer>> getPaths() {
        return paths;
    }

    public void setPaths(List<List<Integer>> paths) {
        this.paths = paths;
    }

    public void printPaths() {
        for (List<Integer> path : paths) {
            for (int node : path) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
