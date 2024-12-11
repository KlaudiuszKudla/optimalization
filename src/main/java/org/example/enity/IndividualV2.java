package org.example.enity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.enity.ModulationCalculator.calculateSlotsAndTransceivers;

public class IndividualV2 {

    private List<Demand> demands;
    private Topology topology;
    private CandidatePaths candidatePaths;
    private int iteration = 0;
    private int usedTranscivers;

    public IndividualV2(List<Demand> demands, Topology topology, CandidatePaths candidatePaths) {
        this.demands = demands;
        this.topology = topology;
        this.candidatePaths = candidatePaths;
    }

    public void generateFirsFit(){
        for(Demand demand : demands){
            this.getCandidatePathsForDemand(demand);
        }
        System.out.println(topology.getEdges());
        System.out.println("------------------------------");
        System.out.println(usedTranscivers);

    }



    public void generateRandomPath(Demand demand){
        int startNode = demand.getStartingNode();
        int endNode = demand.getEndingNode();
        Double bitrate = demand.getBitrates().get(iteration);

    }

    public void getCandidatePathsForDemand(Demand demand){
        int startNode = demand.getStartingNode();
        int endNode = demand.getEndingNode();
        Double bitrate = demand.getBitrates().get(0);
        int candidatePathStartingRow =  startNode * endNode * CandidatePaths.NUMBER_OF_CANDIDATE_PATHS;
        List<List<Integer>> candidatePaths = new ArrayList<>();
        for(int i = 0; i < CandidatePaths.NUMBER_OF_CANDIDATE_PATHS; i++){
            List<Integer> candidatePath = this.candidatePaths.getPaths().get(candidatePathStartingRow + i);
            candidatePaths.add(candidatePath);
        }

        boolean isTrue = false;
        while(!isTrue){
            try {
                List<Integer> choosenPath = getRandomCandidatePath(candidatePaths);
                List<Edge> choosenEdges = getNodePathByChoosenPath(choosenPath);
                int cost = calculateCostPath(choosenEdges);
                ModulationCalculator.Result result = calculateSlotsAndTransceivers(cost, bitrate);
                System.out.println(result.slots);
                System.out.println(result.transceivers);
                isTrue = OpticalSpectrum.canAllocateSlots(choosenEdges, result.slots);
                if(isTrue){
                this.usedTranscivers += result.transceivers;
                }
//                System.out.println(result);
                System.out.println(usedTranscivers);
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }






//        System.out.println(choosenEdges);

//        System.out.println(cost);


    }

    public List<Integer> getRandomCandidatePath(List<List<Integer>> candidatePaths){
        Random rand = new Random();
        return candidatePaths.get(rand.nextInt(candidatePaths.size()));
    }

    public int calculateCostPath(List<Edge> choosenEdges) {
        int  cost = 0;
        for(int i = 0; i < choosenEdges.size(); i++){
            cost += choosenEdges.get(i).getCost();
        }
        return cost;
    }

    public List<Edge> getNodePathByChoosenPath(List<Integer> choosenPath){
        List<Edge> usedEdges = new ArrayList<>();
        for(int i = 0; i < choosenPath.size(); i++){
            if(choosenPath.get(i) == 1){
                usedEdges.add(topology.getEdges().get(i));
            }
        }
//        System.out.println("Used edge" +  usedEdges);
        return usedEdges;
    }

    public void createConnection(List<Edge> choosenEdges){
        OpticalSpectrum.canAllocateSlots(choosenEdges, 6);
//        for(Edge choosenEdge: choosenEdges){
//
//        }
    }
}


