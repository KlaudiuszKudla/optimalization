package org.example.enity;

import java.util.ArrayList;
import java.util.List;

public class Modulation {

    private String name;
    private int distanceLimit;
    private int bitrate;
    private int slots;

    // Konstruktor
    public Modulation(String name, int distanceLimit, int bitrate, int slots) {
        this.name = name;
        this.distanceLimit = distanceLimit;
        this.bitrate = bitrate;
        this.slots = slots;
    }

    // Gettery i Settery
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistanceLimit() {
        return distanceLimit;
    }

    public void setDistanceLimit(int distanceLimit) {
        this.distanceLimit = distanceLimit;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "ModulationTable{" +
                "modulation='" + name + '\'' +
                ", distanceLimit=" + (distanceLimit == Integer.MAX_VALUE ? "no limit" : distanceLimit) +
                ", bitrate=" + bitrate +
                ", slots=" + slots +
                '}';
    }

    // Metoda statyczna do generowania tabeli
    public static List<Modulation> getDefaultTable() {
        List<Modulation> table = new ArrayList<>();
        table.add(new Modulation("QPSK", Integer.MAX_VALUE, 200, 6));
        table.add(new Modulation("8-QAM", Integer.MAX_VALUE, 400, 9));
        table.add(new Modulation("16-QAM", 800, 400, 6));
        table.add(new Modulation("16-QAM", 1600, 600, 9));
        table.add(new Modulation("32-QAM", 200, 800, 9));
        return table;
    }

    // Metoda zwracająca dane na podstawie dystansu i bitrate
    public static Modulation getModulationForDistanceAndBitrate(int distance, Double bitrate) {
        List<Modulation> table = getDefaultTable();
        for (Modulation entry : table) {
            if ((entry.getDistanceLimit() == Integer.MAX_VALUE || distance <= entry.getDistanceLimit())
                    && bitrate <= entry.getBitrate()) {
                return entry;
            }
        }
        return null; // Zwraca null, jeśli nie znaleziono odpowiedniego dopasowania
    }
}
