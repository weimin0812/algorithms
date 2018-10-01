package com.free.algorithms.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author weimin02
 * @date 2018/9/30
 * @project algorithms
 */
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(Graph graph, int s) {
        marked = new boolean[graph.getV()];
        edgeTo = new int[graph.getV()];
        distTo = new int[graph.getV()];
        validateVertex(s);
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getV(); i++) {
            distTo[i] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.getAdj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= edgeTo.length) {
            throw new IllegalArgumentException("Vertex " + v + " is between 0 and " + (edgeTo.length - 1));
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }
}
