package algos.sort;

public class Quick {
    private static final int CUTOFF = 10;

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        // start i, j outside the range
        // since element at lo is pivot, i at lo is out of the range
        int i = lo, j = hi + 1;

        while (true) {
            // Move the left pointer as long as the element is less than pivot
            // (we stop at equal to prevent imbalanced partitions during
            // duplicates of pivot)
            while (SortUtils.less(a[++i], a[lo]))
                if (i == hi)
                    break;

            // Move the right pointer as long as the element is greater than pivot
            while (SortUtils.less(a[lo], a[--j]))
                if (j == lo)
                    break;

            // If i and j cross over, we break
            if (i >= j)
                break;

            // Exchange the elements at i and j
            SortUtils.swap(a, i, j);
        }

        // Put pivot in its place and return the index
        SortUtils.swap(a, lo, j);
        return j;
    }

    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        // Cutoff to insertion sort if less than cutoff
        if (hi - lo + 1 <= CUTOFF) {
            Insertion.sort(a, lo, hi);
            return;
        }

        // Partition and sort left and right parts recursively
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        SortUtils.shuffle(a);
        sort(a, 0, a.length - 1);
    }
}
