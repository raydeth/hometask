package com.github.raydeth;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

//    public static void main(String[] args) {
//        int bound = 100_000;
////        int bound = 20;
//
//        Integer[] a = new Integer[bound];
//        for (int i = 0; i < bound; i++) {
//            a[i] = ThreadLocalRandom.current().nextInt(0, bound);
//        }
//
//        MergeSort mergeSort = new MergeSort();
////        MergeForkJoinSort mergeSort = new MergeForkJoinSort();
//        long start = System.currentTimeMillis();
//        mergeSort.sort(a);
//        System.out.println((System.currentTimeMillis() - start) / 100);
//        for (int i = 0; i < 15; i++) {
//            System.out.print(a[i] + " ");
//        }
//    }

    public static void main(String[] args) {
        int bound = 100_000;
//        int bound = 20;

        Integer[] a = new Integer[bound];
//        int[] a = new int[bound];
        for (int i = 0; i < bound; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, bound);
        }

//        MergeSort mergeSort = new MergeSort();
        MergeForkJoinSort mergeSort = new MergeForkJoinSort(a);
//        MergeSortAction mergeSort = new MergeSortAction(a);
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mergeSort);
//        mergeSort.sort(a);
        System.out.println((System.currentTimeMillis() - start) / 100);
        for (int i = 0; i < 15; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
