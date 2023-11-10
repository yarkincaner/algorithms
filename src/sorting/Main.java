package sorting;

import utils.GenerateArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = GenerateArray.generateIntArray(20);
        System.out.println(Arrays.toString(arr));
        quickSort_lomuto(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] A) {
        long startTime = System.currentTimeMillis();
        InsertionSort.sortInt(A);
        long endTime = System.currentTimeMillis();
        System.out.println("Insertion sort time: " + (endTime - startTime) + "ms");
    }

    public static void mergeSort(int[] A) {
        long startTime = System.currentTimeMillis();
        MergeSort.sortInt(A, 0, A.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Merge sort time: " + (endTime - startTime) + "ms");
    }

    public static void quickSort_hoare(int[] A) {
        long startTime = System.currentTimeMillis();
        QuickSort.hoare_sort(A, 0, A.length-1);
        long endTime = System.currentTimeMillis();
        System.out.println("Quicksort with hoare's partition runtime: " + (endTime - startTime) + "ms");
    }

    public static void quickSort_lomuto(int[] A) {
        long startTime = System.currentTimeMillis();
        QuickSort.lomuto_sort(A, 0, A.length-1);
        long endTime = System.currentTimeMillis();
        System.out.println("Quicksort with lomuto's partition runtime: " + (endTime - startTime) + "ms");
    }
}
