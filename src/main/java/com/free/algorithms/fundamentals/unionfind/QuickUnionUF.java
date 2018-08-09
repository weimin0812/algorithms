package com.free.algorithms.fundamentals.unionfind;

import java.util.Scanner;

/**
 * @author weimin02
 * @date 2018/8/8
 * @project algorithms
 */
public class QuickUnionUF {
    private int[] parent;
    private int count;

    public QuickUnionUF(int n) {
        this.count = n;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public int find(int p){
        validate(p);
        while(p != parent[p]){
            p = parent[p];
        }

        return p;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n){
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public boolean connected(int p, int q){
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ){
            return;
        }

        parent[rootP] = rootQ;
        count--;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        QuickUnionUF quickUnionUF = new QuickUnionUF(n);

        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (quickUnionUF.connected(p, q)) {
                continue;
            }

            quickUnionUF.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println(quickUnionUF.count() + " components");
    }
}
