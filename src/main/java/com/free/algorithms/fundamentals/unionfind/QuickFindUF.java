package com.free.algorithms.fundamentals.unionfind;

import java.util.Scanner;

/**
 * @author weimin02
 * @date 2018/8/8
 * @project algorithms
 */
public class QuickFindUF {
    private int[] id;
    private int count;

    public QuickFindUF(int n) {
        this.count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pId = id[p];
        int qId = id[q];

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }

        count--;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        QuickFindUF quickFindUF = new QuickFindUF(n);

        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (quickFindUF.connected(p, q)) {
                continue;
            }

            quickFindUF.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println(quickFindUF.count() + " components");
    }


}
