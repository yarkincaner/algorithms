package sorting;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = generateArray(10);
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] generateArray(int size) {
        Random r = new Random();
        int[] A = new int[size];

        for (int i = 0; i < size; i++) {
            A[i] = r.nextInt(30);
        }

        return A;
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
