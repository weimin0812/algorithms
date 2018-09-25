package com.free.algorithms.sorting;

/**
 * @author weimin02
 * @date 2018/9/20
 * @project algorithms
 */
public class MergeBU {
    public static void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        Comparable[] aux = new Comparable[a.length];
        int n = a.length;
        for (int len = 1; len < n; len = len * 2) {
            for (int lo = 0; lo < n - len; lo += 2 * len) {
                int hi = Math.min(n - 1, lo + 2 * len - 1);
                int mid = lo + (hi - lo) / 2;
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int l = lo;
        int h = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (l > mid) {
                a[k] = aux[h++];
            } else if (h > hi) {
                a[k] = aux[l++];
            } else if (less(aux[h], aux[l])) {
                a[k] = aux[h++];
            } else {
                a[k] = aux[l++];
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
