package data_structures.priority_queue;

import algos.sort.SortUtils;
import data_structures.testing.TestUtils;

public class TestPQ {
    private static void testMaxPQ() {
        for (int size = 2; size <= 1024; size *= 2) {
            Integer[] elements = new Integer[size];
            for (int i = 0; i < elements.length; i++) {
                elements[i] = i;
            }
            SortUtils.shuffle(elements);

            MaxPQ<Integer> maxPQ = new MaxPQ<>();
            for (Integer element : elements) {
                maxPQ.insert(element);
            }

            MaxPQ<Integer> maxPQ2 = new MaxPQ<>(elements);

            for (int i = size - 1; i >= 0; i--) {
                TestUtils.doAssertion(maxPQ.delMax() == i);
                TestUtils.doAssertion(maxPQ2.delMax() == i);
            }
        }
    }

    private static void testMinPQ() {
        for (int size = 2; size <= 1024; size *= 2) {
            Integer[] elements = new Integer[size];
            for (int i = 0; i < elements.length; i++) {
                elements[i] = i;
            }
            SortUtils.shuffle(elements);

            MinPQ<Integer> minPQ = new MinPQ<>();
            for (Integer element : elements) {
                minPQ.insert(element);
            }

            MinPQ<Integer> minPQ2 = new MinPQ<>(elements);

            for (int i = 0; i < size; i++) {
                TestUtils.doAssertion(minPQ.delMin() == i);
                TestUtils.doAssertion(minPQ2.delMin() == i);
            }
        }
    }

    public static void main(String[] args) {
        testMaxPQ();
        testMinPQ();
        System.out.println("Testing complete");
    }
}
