package com.free.algorithms.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author weimin02
 * @date 2018/9/25
 * @project algorithms
 */
public class MinPQ<Key> implements Iterable<Key> {
    /**
     * store items at indices 1 to n
     */
    private Key[] pq;

    /**
     * number of items on priority queue
     */
    private int n;

    /**
     * optional comparator
     */
    private Comparator<Key> comparator;

    public MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCapacity, Comparator<Key> comparator) {
        this(initCapacity);
        this.comparator = comparator;
    }

    public MinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MinPQ(Key[] keys) {
        n = keys.length;
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }

        assert isMinHeap();
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > n) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && greater(k, left)) {
            return false;
        }

        if (right <= n && greater(k, right)) {
            return false;
        }

        return isMinHeap(left) && isMinHeap(right);
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int left = 2 * k;
            if (left < 2 * n && greater(left, left + 1)) {
                left = left + 1;
            }

            if (!greater(k, left)) {
                break;
            }

            exch(k, left);
            k = left;
        }

    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        }

        return comparator.compare(pq[i], pq[j]) > 0;
    }


    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority Queue underflow");
        }

        return pq[1];
    }

    public void insert(Key x) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }

        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }

        pq = temp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Key delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority Queue underflow");
        }

        Key min = pq[1];
        exch(1, n);
        n = n - 1;
        sink(1);
        pq[n + 1] = null;
        if (n > 0 && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        return min;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MinPQ<Key> copy;

        public HeapIterator() {
            if (comparator == null) {
                copy = new MinPQ<>(size());
            } else {
                copy = new MinPQ<>(size(), comparator);
            }

            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return copy.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Priority Queue underflow");
            }

            return copy.delMin();

        }
    }


}
