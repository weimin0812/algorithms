package com.free.algorithms.graphs;

import edu.princeton.cs.algs4.Bag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author weimin02
 * @date 2018/9/30
 * @project algorithms
 */
public class Graph {
    private final int v;
    private int e;
    private List<Integer>[] adj;

    public Graph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("v should be greater than 0");
        }

        this.v = v;
        e = 0;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }


    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public List<Integer> getAdj(int v) {
        validateVertex(v);
        return adj[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v > this.v) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (v - 1));
        }
    }


    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        adj[w].add(v);
        e++;
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
}
