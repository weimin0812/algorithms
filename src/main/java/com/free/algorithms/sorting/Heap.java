package com.free.algorithms.sorting;

/**
 * @author weimin02
 * @date 2018/9/25
 * @project algorithms
 */
public class Heap {
    public static void sort(Comparable[] a){
        if(a == null || a.length <= 1){
            return;
        }

        for (int i = (a.length-2)/2; i >= 0; i--) {
            sink(a,i,a.length-1);
        }

        int n = a.length-1;
        while(n >= 1){
            exch(a,0,n);
            n--;
            sink(a,0,n);
        }
    }

    private static void sink(Comparable[] a, int i, int lastIndex) {
        while(i*2 <= lastIndex){
            int left = i*2;
            int right = i*2+1;
            int larger = left;
            if(right <= lastIndex && a[left].compareTo(a[right]) < 0){
                larger = right;
            }

            if(!(a[i].compareTo(a[larger]) < 0)){
                break;
            }

            exch(a,i,larger);
            i = larger;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
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
        int length = 10;
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = (int) (Math.random() * 10);
        }

        sort(a);
        System.out.println("a is sorted? : " + isSorted(a));
    }


}
