package com.free.algorithms.graphs;

import org.omg.CORBA.INTERNAL;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author weimin02
 * @date 2018/9/30
 * @project algorithms
 */
public class NonrecursiveDFS {
    private boolean[] marked;
    private int count = 0;

    public NonrecursiveDFS(Graph graph, int s) {
        marked = new boolean[graph.getV()];
        Iterator<Integer>[] adj = new Iterator[graph.getV()];
        for (int i = 0; i < graph.getV(); i++) {
            adj[i] = graph.getAdj(i).iterator();
        }

        marked[s] = true;
        count = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    stack.push(w);
                    marked[w] = true;
                    count++;
                }
            } else {
                stack.pop();
            }

        }

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
        NonrecursiveDFS nonrecursiveDFS = new NonrecursiveDFS(graph, 0);
        if (nonrecursiveDFS.getCount() != graph.getV()) {
            System.out.println("Not connected");
        } else {
            System.out.println("Connected");
        }
    }
}
