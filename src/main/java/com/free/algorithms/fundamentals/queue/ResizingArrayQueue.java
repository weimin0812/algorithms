package com.free.algorithms.fundamentals.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author weimin02
 * @date 2018/9/13
 * @project algorithms
 */
public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int first;
    private int last;

    public ResizingArrayQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[i];
        }

        q = temp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item) {
        if (q.length == n) {
            resize(q.length * 2);
        }

        q[last] = item;
        last++;
        if (last == q.length) {
            last = 0;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        if (n > 0 && n == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        return q[first];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }


    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }


}
