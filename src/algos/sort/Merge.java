package algos.sort;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Merge {
    private final static int CUTOFF = 7;

    public static <T extends Comparable<T>> void merge(T[] arr, T[] aux, int lo, int mid, int hi) {
        // Copy elements from arr to aux
        for (int i = lo; i <= hi; i++) {
            aux[i] = arr[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            // If i reaches the end
            // the remaining elements are only from the second half
            // which are already there
            if (i > mid)
                break;

            // if j reaches the end
            else if (j > hi)
                arr[k] = aux[i++];

            // if aux[i] > aux[j]
            else if (SortUtils.less(aux[j], aux[i]))
                arr[k] = aux[j++];

            // if aux[i] <= aux[j]
            else
                arr[k] = aux[i++];
        }
    }

    private static <T extends Comparable<T>> void sort(T[] arr, T[] aux, int lo, int hi) {
        // Overhead for merge sort is high on smaller arrays
        // and there are going to be smaller arrays because of recursion
        // so we switch to insertion sort below sizes of a certain cutoff
        if (hi - lo + 1 <= CUTOFF) {
            Insertion.sort(arr, lo, hi);
            return;
        }

        // Sort left and right halves
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);

        // Stop if already sorted
        // if arr[mid] <= arr[mid + 1]
        // if (!SortUtils.less(arr[mid + 1], arr[mid]))
        // return;

        // Merge both halves
        merge(arr, aux, lo, mid, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        T[] aux = (T[]) new Comparable[n];
        sort(arr, aux, 0, n - 1);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] { 5, 1, 4, 3, 2, 0 };
        System.out.println(Arrays.toString(array));
        Insertion.sort(array, 1, 4);
        System.out.println(Arrays.toString(array));
    }
}
