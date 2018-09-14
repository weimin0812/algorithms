import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author weimin02
 * @date 2018/9/13
 * @project algorithms
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
        size = 0;
    }

    /**
     * is the randomized queue empty?
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the randomized queue
     *
     * @return
     */
    public int size() {
        return size;

    }

    /**
     * add the item
     *
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can't be null");
        }

        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size] = item;
        size++;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }

        array = temp;
    }

    /**
     * remove and return a random item
     *
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }

        int index = StdRandom.uniform(size);
        Item remove = array[index];
        array[index] = array[size - 1];
        array[size - 1] = null;
        size--;
        if (!isEmpty() && size == array.length / 4) {
            resize(array.length / 2);
        }

        return remove;
    }

    /**
     * return a random item (but do not remove it)
     *
     * @return
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }

        Item sample = array[StdRandom.uniform(size)];
        return sample;
    }

    /**
     * return an independent iterator over items in random order
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator(size, array);
    }


    private class RandomizedIterator implements Iterator<Item> {
        private int size;
        private Item[] array;

        public RandomizedIterator(int size, Item[] array) {
            this.size = size;
            this.array = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                this.array[i] = array[i];
            }
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return size > 0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more item");
            }

            int index = StdRandom.uniform(size);
            Item item = array[index];
            array[index] = array[size - 1];
            array[size - 1] = null;
            size--;
            return item;

        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove unsupported");

        }
    }
}