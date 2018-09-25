package com.free.algorithms.sorting;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author weimin02
 * @date 2018/9/25
 * @project algorithms
 */
public class MaxPQ<Key> implements Iterable<Key> {
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

    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this(initCapacity);
        this.comparator = comparator;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[n + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }

        assert isMaxHeap();
    }

    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if (k > n) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;
        if (left < n && less(k, left)) {
            return false;
        }

        if (right < n && less(k, right)) {
            return false;
        }

        return isMaxHeap(left) && isMaxHeap(right);
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int leftChild = 2 * k;
            if (leftChild < n && less(leftChild, leftChild + 1)) {
                leftChild++;
            }

            if (!less(k, leftChild)) {
                break;
            }

            exch(k, leftChild);
            k = leftChild;
        }
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }


    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority Queue underflow");
        }
        return pq[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void insert(Key x) {
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }

        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i < n; i++) {
            temp[i] = pq[i];
        }

        pq = temp;
    }

    public Key delMax(){
        if(isEmpty()){
            throw new NoSuchElementException("Priority Queue underflow");
        }

        Key max = pq[1];
        exch(1,n);
        n = n-1;
        sink(1);
        pq[n+1] = null;
        if(n > 0 && (n == (pq.length-1)/4)){
            resize(pq.length/2);
        }

        assert isMaxHeap();
        return max;
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MaxPQ<Key> copy;

        public HeapIterator() {
            if(comparator == null){
                copy = new MaxPQ<>(size());
            }else{
                copy = new MaxPQ<>(size(), comparator);
            }

            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext(){
            return copy.isEmpty();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

        @Override
        public Key next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            return copy.delMax();

        }


    }


    public int size(){
        return n;
    }


}
