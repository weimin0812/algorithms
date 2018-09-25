package com.free.algorithms.sorting;

/**
 * @author weimin02
 * @date 2018/9/17
 * @project algorithms
 */
public class Shell {
    private Shell() {

    }

    public static void sort(Comparable[] a) {
        int n = a.length;

        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h > 0) {
            for (int i = h; i < n; i++) {
                for (int j = i; j > h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
        }
    }

    private static void exch(Object[] a, int j, int i) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100);
        }

        sort(a);
    }
}
