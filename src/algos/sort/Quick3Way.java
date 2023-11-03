package algos.sort;

public class Quick3Way {
    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        // 3 way partition (like the dutch flag problem)
        int lt = lo, gt = hi;
        T pivot = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(pivot);
            // If a[i] < pivot exchange a[lt] with a[i] and increment li, i
            if (cmp < 0)
                SortUtils.swap(a, lt++, i++);

            // If a[i] > pivot exchange a[gt] with a[i] and decrement gt
            // We don't increment i because the element which came from gt
            // will be in i and it will be processed in the next iteration
            else if (cmp > 0)
                SortUtils.swap(a, i, gt--);

            // If a[i] == pivot, we increment i
            else
                i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        SortUtils.shuffle(a);
        sort(a, 0, a.length - 1);
    }
}
