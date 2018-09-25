package com.free.algorithms.sorting;

import java.util.Comparator;

/**
 * @author weimin02
 * @date 2018/9/17
 * @project algorithms
 */
public class Insertion {

    /**
     * this class should not be instantiated
     */
    private Insertion(){

    }

    public static void sort(Comparable[] a){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
            assert isSorted(a, 0, i);
        }

        assert isSorted(a);
    }


    public static void sort(Comparable[] a, int lo, int hi){
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]) ; j++) {
                exch(a, j, j-1);
            }
        }

        assert isSorted(a, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1], comparator) ; j--) {
                exch(a, j , j-1);
            }

            assert isSorted(a, 0, i, comparator);
        }

        assert isSorted(a, comparator);
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length, comparator);
    }

    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo+1; i < hi; i++) {
            if (less(a[i], a[i-1], comparator)){
                return false;
            }
        }

        return true;
    }

    private static boolean less(Object o, Object o1, Comparator comparator) {
        return comparator.compare(o, o1) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i < hi; i++) {
            if (less(a[i], a[i-1])){
                return false;
            }
        }

        return true;
    }

    private static void exch(Object[] a, int j, int i) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }


    public static int[] indexSort(Comparable[] a){
        int n = a.length;
        int[] index = new int[n];

        for (int i = 0; i < n; i++) {
            index[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[index[j]], a[index[j-1]]) ; j++) {
                exch(index, j, j-1);
            }
        }

        return index;
    }

    public static void exch(int[] a, int i, int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


}
