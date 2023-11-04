package sorting;

import utils.GenerateArray;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = GenerateArray.generateIntArray(10);
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
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
}
