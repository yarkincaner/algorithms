package sorting;

import utils.Swap;

import java.util.Random;

public class RandomizedQuickSort {
    public static void quicksort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q);
            quicksort(A, q+1, r);
        }
    }

    public static int partition(int[] A, int p, int r) {
        Random random = new Random();
        int s = p + random.nextInt(r - p + 1);
        Swap.swap(A, p, s);
        return QuickSort.hoare_partition(A, p, r);
    }
}
