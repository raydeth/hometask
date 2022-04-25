package com.github.raydeth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeForkJoinSort extends RecursiveAction{


    private volatile Comparable[] a;
    private volatile Comparable[] aux;
    private volatile int lo;
    private volatile int hi;

    public MergeForkJoinSort(Comparable[] a) {
        this.a = a;
        this.aux = a.clone();
        this.lo = 0;
        this.hi = a.length - 1;
    }

    public MergeForkJoinSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        this.a = a;
        this.aux = aux;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute() {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        MergeForkJoinSort left = new MergeForkJoinSort(a, aux, lo, mid);
        MergeForkJoinSort right = new MergeForkJoinSort(a, aux, mid + 1, hi);
        left.fork();

        right.compute();
        left.join();
        merge(a, aux, lo, mid, hi);
    }

    public void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }
}
