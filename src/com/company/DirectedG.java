package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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

    public ArrayList<Integer> findCycle() {
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Integer> cycle = new ArrayList<>();

        for (int vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                if (dfs(vertex, visited, path, cycle)) {
                    Collections.reverse(cycle);
                    cycle.add(vertex);
                    return cycle;
                }
            }
        }

        return null; // indicates the graph is acyclic
    }

    private boolean dfs(int vertex, ArrayList<Integer> visited, ArrayList<Integer> path, ArrayList<Integer> cycle) {
        visited.add(vertex);
        path.add(vertex);

        for (int neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, visited, path, cycle)) {
                    cycle.add(vertex);
                    return true;
                }
            } else if (path.contains(neighbor)) {
                cycle.add(vertex);
                return true;
            }
        }

        path.remove(vertex);

        return false;
    }
}


