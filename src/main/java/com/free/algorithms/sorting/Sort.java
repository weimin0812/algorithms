package com.free.algorithms.sorting;

/**
 * @author weimin02
 * @date 2018/9/26
 * @project algorithms
 */
public class Sort {
    public static void main(String[] args) {
        int length = 100;
        int maxValue = 78;
        int[] array = generateArray(length, maxValue);
        bubbleSort(array);
        System.out.println("After bubble sort array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        selectSort(array);
        System.out.println("After select sort array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        insertSort(array);
        System.out.println("After insert sort array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        shellSort(array);
        System.out.println("After shell sort array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        mergeSort(array);
        System.out.println("After merge sort array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        mergeSortBottomUp(array);
        System.out.println("After merge sort bottom up array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        quickSort(array);
        System.out.println("After quick sort array is sorted?: " + isSorted(array));

        array = generateArray(length, maxValue);
        heapSort(array);
        System.out.println("After heap sort array is sorted?: " + isSorted(array));

    }

    private static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int lastIndex = array.length - 1;
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            sink(array, i, lastIndex);
        }

        for (; lastIndex > 0; ) {
            swap(array, 0, lastIndex);
            lastIndex--;
            sink(array, 0, lastIndex);
        }
    }

    private static void sink(int[] array, int i, int lastIndex) {
        while (i * 2 + 1 <= lastIndex) {
            int left = i * 2 + 1;
            int right = 2 * i + 2;
            int greater = left;
            if (right <= lastIndex && less(array, left, right)) {
                greater = right;
            }

            if (less(array, i, greater)) {
                swap(array, i, greater);
                i = greater;
            } else {
                break;
            }
        }
    }

    private static void quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int lo, int hi) {
        if (lo < hi) {
            int j = partition(array, lo, hi);
            quickSort(array, lo, j - 1);
            quickSort(array, j + 1, hi);
        }
    }

    private static int partition(int[] array, int lo, int hi) {
        int pivort = array[lo];

        while (lo < hi) {
            while (array[hi] >= pivort && lo < hi) {
                hi--;
            }

            array[lo] = array[hi];

            while (array[lo] <= pivort && lo < hi) {
                lo++;
            }
            array[hi] = array[lo];
        }

        array[lo] = pivort;
        return lo;
    }

    private static void mergeSortBottomUp(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int[] aux = new int[array.length];
        for (int len = 1; len < array.length; len = len * 2) {
            for (int lo = 0; lo < array.length - len; lo = lo + len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + 2 * len - 1, array.length - 1);
                merge(array, aux, lo, mid, hi);
            }
        }
    }

    private static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int[] aux = new int[array.length];
        mergeSort(array, aux, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            mergeSort(array, aux, lo, mid);
            mergeSort(array, aux, mid + 1, hi);
            merge(array, aux, lo, mid, hi);
        }
    }

    private static void merge(int[] array, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = array[i];
        }

        int l = lo;
        int h = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (l > mid) {
                array[i] = aux[h++];
            } else if (h > hi) {
                array[i] = aux[l++];
            } else if (less(aux, h, l)) {
                array[i] = aux[h++];
            } else {
                array[i] = aux[l++];
            }

        }
    }

    private static void shellSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }

        for (; h >= 1; h = h / 3) {
            for (int i = h; i < array.length; i++) {
                for (int j = i; j >= h && less(array, j, j - h); j -= h) {
                    swap(array, j, j - h);
                }
            }
        }
    }

    private static void insertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 && less(array, j, j - 1); j--) {
                swap(array, j, j - 1);
            }
        }
    }

    private static void selectSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array, j, min)) {
                    min = j;
                }
            }

            if (min != i) {
                swap(array, i, min);
            }
        }
    }

    private static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            boolean sorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (less(array, j + 1, j)) {
                    sorted = false;
                    swap(array, j + 1, j);
                }
            }

            if (sorted) {
                break;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static boolean less(int[] array, int i, int j) {
        return array[i] - array[j] < 0;
    }

    public static int[] generateArray(int length, int maxValue) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * maxValue);
        }
        return array;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
