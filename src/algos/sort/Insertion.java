package algos.sort;

public class Insertion {
    public static <T extends Comparable<T>> void sort(T[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (SortUtils.less(arr[j], arr[j - 1]))
                    SortUtils.swap(arr, j, j - 1);
                else
                    break;
            }
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
