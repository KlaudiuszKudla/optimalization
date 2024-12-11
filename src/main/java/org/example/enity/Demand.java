package org.example.enity;

import java.util.Comparator;
import java.util.List;

public class Demand {

    public int startingNode;
    public int endingNode;
    public List<Double> bitrates;

    public static Comparator<Demand> compareByBitrateIndexDesc(int index) {
        return (d1, d2) -> {
            if (index < 0 || index >= d1.getBitrates().size() || index >= d2.getBitrates().size()) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of range for bitrates list.");
            }
            double bitrate1 = d1.getBitrates().get(index);
            double bitrate2 = d2.getBitrates().get(index);
            return Double.compare(bitrate2, bitrate1);
        };
    }

    public static Comparator<Demand> compareByBitrateIndexAsc(int index) {
        return (d1, d2) -> {
            if (index < 0 || index >= d1.getBitrates().size() || index >= d2.getBitrates().size()) {
                throw new IndexOutOfBoundsException("Index " + index + " is out of range for bitrates list.");
            }
            double bitrate1 = d1.getBitrates().get(index);
            double bitrate2 = d2.getBitrates().get(index);
            return Double.compare(bitrate2, bitrate1);
        };
    }

    public int getStartingNode() {
        return startingNode;
    }

    public void setStartingNode(int startingNode) {
        this.startingNode = startingNode;
    }

    public int getEndingNode() {
        return endingNode;
    }

    public void setEndingNode(int endingNode) {
        this.endingNode = endingNode;
    }

    public List<Double> getBitrates() {
        return bitrates;
    }

    public void setBitrates(List<Double> bitrates) {
        this.bitrates = bitrates;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "startingNode=" + startingNode +
                ", endingNode=" + endingNode +
                ", bitrates=" + bitrates +
                '}';
    }
}
