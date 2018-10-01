package com.free.algorithms.graphs;

/**
 * @author weimin02
 * @date 2018/10/1
 * @project algorithms
 */
public class CC {
    public boolean[] marked;
    public int[] id;
    public int[] size;
    public int count;

    public CC(Graph graph) {
        marked = new boolean[graph.getV()];
        id = new int[graph.getV()];
        size = new int[graph.getV()];

        for (int v = 0; v < graph.getV(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;

        for (int w : graph.getAdj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (marked.length - 1));
        }
    }

    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    public int count() {
        return count;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(10);
        CC cc = new CC(graph);
        System.out.println(cc.count());
    }

}
