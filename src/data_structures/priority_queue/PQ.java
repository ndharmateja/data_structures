package data_structures.priority_queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings({ "unchecked", "unused" })
abstract class PQ<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 5;

    protected T[] pq;
    private int size;

    protected PQ() {
        pq = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    protected PQ(T[] keys) {
        size = keys.length;
        pq = (T[]) new Comparable[size + 1];
        for (int i = 1; i < size + 1; i++) {
            pq[i] = keys[i - 1];
        }
        buildHeap();
    }

    public void insert(T t) {
        // If array is full
        // resize so that 2*size elements will fit
        int currCapacity = pq.length - 1;
        if (size == currCapacity) {
            int newSize = 2 * currCapacity + 1;
            resize(newSize);
        }
        pq[++size] = t;
        swim(size);
    }

    // Removes the max element in max heap and
    // the min element in the min heap
    protected T dequeue() {
        // If heap is empty throw exception
        if (isEmpty())
            throw new NoSuchElementException();

        // Swap the max element with the last element
        // and sink from the root
        T max = pq[1];
        swap(1, size--);
        sink(1);
        pq[size + 1] = null; // to prevent loitering

        // Resize if necessary
        int currCapacity = pq.length - 1;
        if (size < currCapacity / 3) {
            int newSize = (currCapacity / 2) + 1;
            resize(newSize);
        }

        return max;
    }

    protected T peek() {
        // If heap is empty throw exception
        if (isEmpty())
            throw new NoSuchElementException();
        return pq[1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Compare will do "less than" for max heaps
     * and "greater than" for min heaps
     * 
     * @param i
     * @param j
     * @return
     */
    protected abstract boolean compare(int i, int j);

    // Helper methods
    private void resize(int capacity) {
        T[] copy = (T[]) new Comparable[capacity];
        for (int i = 1; i <= size; i++) {
            copy[i] = pq[i];
        }
        pq = copy;
    }

    private void swap(int i, int j) {
        T tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private static int getParent(int k) {
        return k / 2;
    }

    private static int getLeftChild(int k) {
        return 2 * k;
    }

    private static int getRightChild(int k) {
        return 2 * k + 1;
    }

    private void swim(int k) {
        // As long as k has a parent and compare(parent, k) is true
        int parent;
        while (k > 1 && compare(parent = getParent(k), k)) {
            swap(k, parent);
            k = parent;
        }
    }

    private void sink(int k) {
        // As long as 'k' has atleast left child
        while (getLeftChild(k) <= size) {
            int j = getLeftChild(k);

            // If right child exists (exists if left child index is not the last element
            // in array) and less than the left child, update the minIndex to right child
            if (j < size && compare(j, j + 1))
                j++;

            // If element is greater than or equal to the min child stop
            if (!compare(k, j))
                break;

            // If the min child is smaller than the element
            // swap it with the min child and continue sink with the child
            swap(k, j);
            k = j;
        }
    }

    private void buildHeap() {
        for (int k = getParent(size); k >= 1; k--) {
            sink(k);
        }
    }
}
