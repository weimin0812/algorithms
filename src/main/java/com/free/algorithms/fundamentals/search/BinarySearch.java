package com.free.algorithms.fundamentals.search;

/**
 * @author weimin02
 * @date 2018/8/9
 * @project algorithms
 */
public class BinarySearch {
    /**
     * This class should not be instantiated
     */
    private BinarySearch() {

    }

    public int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
