package org.example.enity;

public class ModulationCalculator {

    public static class Result {
        public final int slots;
        public final int transceivers;

        public Result(int slots, int transceivers) {
            this.slots = slots;
            this.transceivers = transceivers;
        }

        @Override
        public String toString() {
            return "Slots: " + slots + ", Transceivers: " + transceivers;
        }
    }

    public static Result calculateSlotsAndTransceivers(int distance, Double bitrateGbps) {
        // Define modulation formats
        class Modulation {
            String name;
            int maxDistance;
            int bitrate;
            int slots;

            Modulation(String name, int maxDistance, int bitrate, int slots) {
                this.name = name;
                this.maxDistance = maxDistance;
                this.bitrate = bitrate;
                this.slots = slots;
            }
        }

        Modulation[] modulations = new Modulation[]{
                new Modulation("QPSK", Integer.MAX_VALUE, 200, 6),
                new Modulation("8-QAM", Integer.MAX_VALUE, 400, 9),
                new Modulation("16-QAM", 800, 400, 6),
                new Modulation("16-QAM", 1600, 600, 9),
                new Modulation("32-QAM", 200, 800, 9)
        };

        Modulation bestModulation = null;
        int minSlots = Integer.MAX_VALUE;
        int minTransceivers = Integer.MAX_VALUE;

        // Evaluate all modulations to find the most efficient one
        for (Modulation modulation : modulations) {
            if (distance <= modulation.maxDistance) {
                int channelsNeeded = (int) Math.ceil((double) bitrateGbps / modulation.bitrate);
                int totalSlots = channelsNeeded * modulation.slots;
                int totalTransceivers = channelsNeeded * 2;

                if (totalSlots < minSlots || (totalSlots == minSlots && totalTransceivers < minTransceivers)) {
                    minSlots = totalSlots;
                    minTransceivers = totalTransceivers;
                    bestModulation = modulation;
                }
            }
        }

        if (bestModulation == null) {
            throw new IllegalArgumentException("No suitable modulation format for the given parameters.");
        }

        return new Result(minSlots, minTransceivers);
    }
}