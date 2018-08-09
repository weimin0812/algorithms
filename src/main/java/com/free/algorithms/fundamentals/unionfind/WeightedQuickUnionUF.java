package com.free.algorithms.fundamentals.unionfind;

import java.util.Scanner;

/**
 * @author weimin02
 * @date 2018/8/8
 * @project algorithms
 */
public class WeightedQuickUnionUF {
    private int[] parent;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        while (parent[p] != p) {
            p = parent[p];
        }
        return p;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        // make smaller root point to large one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        count--;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        WeightedQuickUnionUF weightedQuickUnionUF = new WeightedQuickUnionUF(n);

        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (weightedQuickUnionUF.connected(p, q)) {
                continue;
            }

            weightedQuickUnionUF.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println(weightedQuickUnionUF.count() + " components");
    }


}
