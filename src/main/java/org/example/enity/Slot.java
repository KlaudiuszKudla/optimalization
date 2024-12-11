package org.example.enity;

public class Slot {

    private boolean isAvailable = true;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "isAvailable=" + isAvailable +
                '}';
    }
}
