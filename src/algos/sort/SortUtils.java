package algos.sort;

import java.util.Random;

public class SortUtils {
    public static <T extends Comparable<T>> boolean less(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable<T>> boolean more(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Shuffle an array uniformly randomly using Fisher Yates shuffle.
     * Every permutation has equal probability.
     * 
     * Ref:
     * https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
     * 
     * @param <T>
     * @param arr
     */
    //
    public static <T> void shuffle(T[] arr) {
        int n = arr.length;
        Random r = new Random();

        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = n - 1; i > 0; i--) {

            // Pick a random index from 0 to i
            int j = r.nextInt(i + 1);

            // Swap arr[i] with the element at random index
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (SortUtils.less(arr[i + 1], arr[i]))
                return false;
        }
        return true;
    }
}
