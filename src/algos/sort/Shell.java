package algos.sort;

public class Shell {
    private static <T extends Comparable<T>> void hSort(T[] arr, int h) {
        for (int i = h; i < arr.length; i++) {
            for (int j = i; j >= h; j -= h) {
                if (SortUtils.less(arr[j], arr[j - h]))
                    SortUtils.swap(arr, j, j - h);
                else
                    break;
            }
        }
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;

        // h sequence -> 1, 4, 13, 40, 121, 364..
        int h = 1;
        while (h < n)
            h = 3 * h + 1;

        while (h >= 1) {
            hSort(arr, h);
            h = h / 3;
        }
    }
}
