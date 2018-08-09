package com.free.algorithms.fundamentals.unionfind;

import java.util.Scanner;

/**
 * @author weimin02
 * @date 2018/8/8
 * @project algorithms
 */
public class UF {
    private int[] parent;
    /**
     * rank[i] = rank of subtree rooted at i (never more than 31)
     */
    private byte[] rank;
    private int count;

    public UF(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        this.count = n;

        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            // path compression by halving
            parent[p] = parent[parent[p]];
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

    public int count() {
        return count;
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

        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }

        count--;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        UF uf = new UF(n);
        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (uf.connected(p, q)) {
                continue;
            }

            uf.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println(uf.count() + " components");
    }


}
