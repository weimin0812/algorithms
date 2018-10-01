package com.free.algorithms.graphs;

import java.util.Stack;

/**
 * @author weimin02
 * @date 2018/9/30
 * @project algorithms
 */
public class DepthFirstPaths {
    /**
     * marked[v] = is there an s-v path?
     */
    private boolean[] marked;

    /**
     * edgeTo[v] = last edge on s-v path
     */
    private int[] edgeTo;

    private final int s;

    public DepthFirstPaths(Graph graph, int s) {
        this.s = s;
        edgeTo = new int[graph.getV()];
        marked = new boolean[graph.getV()];

        validateVertex(s);
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.getAdj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    private void validateVertex(int s) {
        if (s < 0 || s >= marked.length) {
            throw new IllegalArgumentException("vertex " + s + " is not between 0 and " + (marked.length - 1));
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        while (v != s) {
            path.push(v);
            v = edgeTo[v];
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        int v = 10;
        Graph graph = new Graph(v);
        for (int i = 0; i < v-1; i++) {
            graph.addEdge(i,i+1);
        }

        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, 0);
        if(depthFirstPaths.hasPathTo(v-1)){
            boolean first = true;
            for (int w : depthFirstPaths.pathTo(v - 1)) {
                if(first){
                    first = false;
                }else{
                    System.out.print(" --> ");
                }
                System.out.print(w);
            }

        }
    }


}
