package org.example.enity;

import java.util.ArrayList;
import java.util.List;

public class Topology {

    private int numberOfNodes;
    private int numberOfEdges;
    private int[][] distanceMatrix;
    private List<Edge> edges;

    public Topology() {
        this.edges = new ArrayList<>();
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    public int[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }

    public void printDistanceMatrix() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                System.out.print(distanceMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setEdges() {
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                if (distanceMatrix[i][j] != 0) {
                    Edge edge = new Edge(1500);
                    edge.setStartingNode(i);
                    edge.setEndingNode(j);
                    edge.setCost(distanceMatrix[i][j]);
                    edges.add(edge);
                }
            }
        }
        for (Edge edge : edges) {
            System.out.println(edge);
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
