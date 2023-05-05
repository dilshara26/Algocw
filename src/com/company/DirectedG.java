package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectedG {

    public  HashMap<Integer, ArrayList<Integer>> adjacencyList;

    public DirectedG() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(int source, int destination) {
        if (!adjacencyList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjacencyList.containsKey(destination)) {
            addVertex(destination);
        }
        adjacencyList.get(source).add(destination);
    }

    public ArrayList<Integer> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    public int size() {
        return adjacencyList.size();
    }

    public boolean hasSink() {
        for (int vertex : adjacencyList.keySet()) {
            if (adjacencyList.get(vertex).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAcyclic(){
        boolean sink = true;
        while (sink){
            int v =removeSink();
            if(v== -1){

               sink = false;
            }
        }
        return adjacencyList.size() == 0;
    }

    public int removeSink() {
        for (int vertex : adjacencyList.keySet()) {
            if (adjacencyList.get(vertex).isEmpty()) {
                adjacencyList.remove(vertex);
                for (int otherVertex : adjacencyList.keySet()) {
                    adjacencyList.get(otherVertex).remove((Integer)vertex);
                }
                return vertex;
            }
        }
        return -1; // indicates no sink was found
    }
}


