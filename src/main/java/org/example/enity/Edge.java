package org.example.enity;

import java.util.ArrayList;
import java.util.List;

public class Edge {

    private int startingNode;
    private int endingNode;
    private int cost;
    private int usedSlots;
    private List<Slot> slots;

    public Edge(int numberOfSlots) {
        slots = new ArrayList<Slot>();
        for (int i = 0; i < numberOfSlots ; i++) {
            Slot slot  = new Slot();
            slots.add(slot);
        }
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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public void setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "usedSlots=" + usedSlots +
                ", cost=" + cost +
                ", endingNode=" + endingNode +
                ", startingNode=" + startingNode +
                '}';
    }
}

