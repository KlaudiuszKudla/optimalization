package org.example.enity;

import java.util.List;

public class OpticalSpectrum {



    public static boolean canAllocateSlots(List<Edge> edges, int requiredSlots) {
        if (edges == null || edges.isEmpty() || requiredSlots <= 0) {
            return false;
        }

        int slotCount = edges.get(0).getSlots().size();

        for (int startSlot = 0; startSlot <= slotCount - requiredSlots; startSlot++) {
            boolean canAllocate = true; // Reset flag at the start of each iteration

            for (Edge edge : edges) {
                List<Slot> slots = edge.getSlots();

                // Check if all requiredSlots are available contiguously
                for (int i = 0; i < requiredSlots; i++) {
                    if (!slots.get(startSlot + i).isAvailable()) {
                        canAllocate = false;
                        break;
                    }
                }
                if (!canAllocate) {
                    break;
                }
            }

            if (canAllocate) {
                // Occupy the slots on all edges
                for (Edge edge : edges) {
                    for (int i = 0; i < requiredSlots; i++) {
                        edge.getSlots().get(startSlot + i).setAvailable(false);
                        edge.setUsedSlots(edge.getUsedSlots() + 1);
                    }
                }
                System.out.println("success");
                return true; // Allocation successful
            }
        }
        return false;
    }

}