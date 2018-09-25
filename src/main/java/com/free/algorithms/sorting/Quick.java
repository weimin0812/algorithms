package com.free.algorithms.sorting;

/**
 * @author weimin02
 * @date 2018/9/21
 * @project algorithms
 */
public class Quick {
    public static void sort(Comparable[] a) {
        if (a == null || a.length < 1) {
            return;
        }

        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo < hi) {
            int pivortIndex = partition(a, lo, hi);
            sort(a, lo, pivortIndex - 1);
            sort(a, pivortIndex + 1, hi);
        }
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable pivort = a[lo];
        while (lo < hi) {
            while (a[hi].compareTo(pivort) >= 0 && lo < hi) {
                hi--;
            }

            a[lo] = a[hi];
            while(a[lo].compareTo(pivort) <= 0 && lo < hi){
                lo++;
            }
            a[hi] = a[lo];
        }
        a[lo] = pivort;
        return lo;
    }

    public static boolean isSorted(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return true;
        }

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int length = 1000;
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = (int) (Math.random() * 1000);
        }

        sort(a);
        System.out.println("a is sorted? : " + isSorted(a));
    }
}
