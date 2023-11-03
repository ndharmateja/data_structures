package data_structures.priority_queue;

import algos.sort.SortUtils;

public class MaxPQ<T extends Comparable<T>> extends PQ<T> {
    public MaxPQ() {
        super();
    }

    public MaxPQ(T[] keys) {
        super(keys);
    }

    @Override
    protected boolean compare(int i, int j) {
        return SortUtils.less(pq[i], pq[j]);
    }

    public T delMax() {
        return dequeue();
    }

    public T max() {
        return peek();
    }
}
