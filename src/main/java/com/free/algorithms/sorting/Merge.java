package com.free.algorithms.sorting;

import edu.princeton.cs.algs4.In;

/**
 * @author weimin02
 * @date 2018/9/20
 * @project algorithms
 */
public class Merge {
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            merge(a, aux, lo, mid, hi);
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return true;
        }

        for (int i = 0; i < a.length - 1; i++) {
            if (less(a[i + 1], a[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int length = 100;
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = (int) (Math.random() * 1000);
        }

        sort(a);
        System.out.println("a is sorted? " + isSorted(a));
    }


}
