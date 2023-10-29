package data_structures.array.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import algos.sort.SortUtils;

public class RandomizedArrayIterator<T> implements Iterator<T> {
    T[] iteratorArray;
    int curr = 0;

    /**
     * Creates a random iterator of the numElements elements
     * starting from head index in the forward direction (circularly iterates from
     * the start once the end is reached)
     * 
     * Note: This creates an independent copy of the required elements
     * 
     * @param array
     * @param head
     * @param numElements
     */
    @SuppressWarnings("unchecked")
    public RandomizedArrayIterator(T[] array, int head, int numElements) {
        // Create an copy array with the size of "size"
        // to store all the elements and copy all elements
        // from the randomized queue to it
        iteratorArray = (T[]) new Object[numElements];
        for (int i = 0; i < numElements; i++) {
            int index = (i + head) % array.length;
            iteratorArray[i] = array[index];
        }

        // Randomly shuffle it
        SortUtils.shuffle(iteratorArray);
    }

    @Override
    public boolean hasNext() {
        return curr < iteratorArray.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return iteratorArray[curr++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
