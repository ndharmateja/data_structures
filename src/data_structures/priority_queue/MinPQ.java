package data_structures.priority_queue;

import algos.sort.SortUtils;

public class MinPQ<T extends Comparable<T>> extends PQ<T> {
    public MinPQ() {
        super();
    }

    public MinPQ(T[] keys) {
        super(keys);
    }

    @Override
    protected boolean compare(int i, int j) {
        return SortUtils.more(pq[i], pq[j]);
    }

    public T delMin() {
        return dequeue();
    }

    public T min() {
        return peek();
    }
}
