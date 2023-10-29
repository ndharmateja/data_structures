package algos.sort;

import java.util.Arrays;

import data_structures.testing.TestUtils;

public class TestSorting {
    private static <T extends Comparable<T>> void testAllSorts(T[] sortedArray) {
        SortUtils.shuffle(sortedArray);
        Insertion.sort(sortedArray);
        TestUtils.doAssertion(SortUtils.isSorted(sortedArray),
                "Insertion sort failed, array not sorted: " + Arrays.toString(sortedArray));

        SortUtils.shuffle(sortedArray);
        Selection.sort(sortedArray);
        TestUtils.doAssertion(SortUtils.isSorted(sortedArray),
                "Selection sort failed, array not sorted: " + Arrays.toString(sortedArray));

        SortUtils.shuffle(sortedArray);
        Shell.sort(sortedArray);
        TestUtils.doAssertion(SortUtils.isSorted(sortedArray),
                "Shell sort failed, array not sorted: " + Arrays.toString(sortedArray));

        SortUtils.shuffle(sortedArray);
        Merge.sort(sortedArray);
        TestUtils.doAssertion(SortUtils.isSorted(sortedArray),
                "Merge sort failed, array not sorted: " + Arrays.toString(sortedArray));
    }

    private static <T extends Comparable<T>> void test() {
        for (int sz = 1; sz < 1026; sz = 2 * sz) {
            for (int trial = 0; trial < 100; trial++) {
                // Test random arrays of sz
                Integer[] array = new Integer[sz];
                for (int i = 0; i < array.length; i++) {
                    array[i] = i;
                }
                testAllSorts(array);

                // Test array with duplicates
                for (int i = 0; i < sz / 3; i++) {
                    array[i] = 0;
                }
                for (int i = sz / 3; i < 2 * sz / 3; i++) {
                    array[i] = 1;
                }
                for (int i = 2 * sz / 3; i < sz; i++) {
                    array[i] = 2;
                }

                testAllSorts(array);
            }
        }

    }

    public static void main(String[] args) {
        test();
        System.out.println("Testing complete");
    }
}
