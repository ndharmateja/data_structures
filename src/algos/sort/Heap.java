package algos.sort;

public class Heap {
    public static <T extends Comparable<T>> void sort(T[] a) {
        // Build heap
        // Start from the parent of the last element in the heap
        // and keep sinking by moving in reverse level order from
        // that element
        int heapSize = a.length;
        for (int k = getParent(heapSize); k >= 1; k--) {
            sink(a, k, heapSize);
        }

        // Extract max by swapping it with the last element of the heap
        // and sink the root and repeat until one element is left in the heap
        // We can stop at one element because that would be the smallest and
        // we don't need to do any sinking
        while (heapSize > 1) {
            swap(a, 1, heapSize);
            sink(a, 1, --heapSize);
        }
    }

    // Heap methods
    // Based on binary heap array representation where indices start at 1
    private static int getParent(int k) {
        return k / 2;
    }

    private static int getLeftChild(int k) {
        return 2 * k;
    }

    private static <T extends Comparable<T>> void sink(T[] a, int k, int heapSize) {
        // As long as 'k' has atleast left child
        while (getLeftChild(k) <= heapSize) {
            int minIndex = getLeftChild(k);

            // If right child exists (exists if left child index is not the last element
            // in array) and less than the left child, update the minIndex to right child
            if (minIndex < heapSize && less(a, minIndex, minIndex + 1))
                minIndex++;

            // If element is greater than or equal to the min child stop
            if (!less(a, k, minIndex))
                break;

            // If the min child is smaller than the element
            // swap it with the min child and continue sink with the child
            swap(a, k, minIndex);
            k = minIndex;
        }
    }

    // Array helper methods
    // Since the actual indices of the array are 0-indexed
    // and the heap is based on 1-indexing, we correct them
    // in the less and swap methods
    private static <T extends Comparable<T>> boolean less(T[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T tmp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = tmp;
    }
}
