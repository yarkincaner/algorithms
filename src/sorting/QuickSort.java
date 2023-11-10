package sorting;

import utils.Swap;

public class QuickSort {
    public static void hoare_sort(int[] A, int p, int r) {
        if (p >= r) return;
        int q = hoare_partition(A, p, r);
        hoare_sort(A, p, q);
        hoare_sort(A, q+1, r);
    }

    private static int hoare_partition(int[] A, int p, int r) {
        int pivot = A[p];
        int i = p-1;
        int j = r+1;
        while (true) {
            do {
                j--;
            } while (A[j] > pivot);
            do {
                i++;
            } while (A[i] < pivot);

            if (i >= j) return j;
            Swap.swap(A, i, j);
        }
    }

    public static void lomuto_sort(int[] A, int p, int r) {
        if (p >= r) return;
        int q = lomuto_partition(A, p, r);
        lomuto_sort(A, p, q-1);
        lomuto_sort(A, q+1, r);
    }

    private static int lomuto_partition(int[] A, int p, int r) {
        int pivot = A[r];
        int i = p-1;
        for (int j = p; j < r; j++) {
            if (A[j] <= pivot) {
                Swap.swap(A, ++i, j);
            }
        }
        Swap.swap(A, ++i, r);
        return i;
    }
}
