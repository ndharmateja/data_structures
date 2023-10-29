package algos.sort;

public class BottomUpMerge {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        // Aux array for space
        int n = arr.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[n];

        // For each sz, merge adjacent subarrays of size sz
        for (int sz = 1; sz < n; sz *= 2) {
            for (int lo = 0; lo < n - sz; lo += (2 * sz)) {
                Merge.merge(arr, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }
}
