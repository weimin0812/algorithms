package com.free.algorithms.graphs;

/**
 * @author weimin02
 * @date 2018/9/30
 * @project algorithms
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.getV()];
        validateVertex(s);
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        count++;
        for (int w : graph.getAdj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }


    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (marked.length - 1));
        }
    }

    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph, 4);
        if (depthFirstSearch.getCount() != graph.getV()) {
            System.out.println("Not connected");
        } else {
            System.out.println("Connected");
        }
    }
}
