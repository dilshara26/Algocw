package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
	// write your code here

       DirectedG d = new DirectedG();
        System.out.println(d.size());




        d.addEdge(1,2);
        d.addEdge(2,3);
        d.addEdge(3,2);

        System.out.println(d.checkAcyclic());
        System.out.println( d.findCycle());

    }
}
