package algos.sort;

public class Selection {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (SortUtils.less(arr[j], arr[minIndex])) {
                    minIndex = j;
                }
            }
            SortUtils.swap(arr, i, minIndex);
        }
    }
}
