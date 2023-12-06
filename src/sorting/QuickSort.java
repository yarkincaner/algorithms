package sorting;

import utils.Swap;

public class QuickSort {
    /**
     * Quicksort with hoare's partition splits array into two pieces from q. Since sizes of these pieces are unknown,
     * it is hard to assume a complexity for quicksort with hoare's partition.
     *
     * In a scenario where array is sorted; hoare's partition always returns the first index. That would result two
     * recursive calls which one of them has size 1 and the other size n-1.
     * That would result in -> T(n) = T(1) + T(n-1) + O(n) and the complexity would be O(n^2).
     * For reversed-sorted input, it would be the opposite as T(n) = T(n-1) + T(1) + O(n); which results in O(n^2)
     * Thus, sorted and reversed-sorted inputs gives us the worst cases as O(n^2).
     *
     * However, we want the average case. The probability of hoare's partition to not select the q index from the first %10
     * and from te last %10 is %80. So if we assume that each recursive call divides array into %10 to %90 for the average case.
     * This gives us T(n) = T(n/10) + T(9n/10) + O(n) which results in O(nlogn).
     *
     * Therefore, average case for Quicksort with Hoare's Partition gives us the complexity for;
     * Worst case: O(n^2)
     * Best and Average case: O(nlogn)
     *
     * @param A input array
     * @param p first index
     * @param r last index
     */
    public static void hoare_sort(int[] A, int p, int r) {
        if (p >= r) return;
        int q = hoare_partition(A, p, r);
        hoare_sort(A, p, q);
        hoare_sort(A, q+1, r);
    }

    /**
     * Hoare's partition selects pivot value from the first index. i begins increasing from the first index until the
     * value is less than pivot, j begins decreasing from the last index until the value is larger than pivot.
     *
     * Complexity: O(n) since it visits every index on input array. There are no worst or best cases.
     *
     * @param A input array
     * @param p first index
     * @param r last index
     * @return a number which indicates left from that index is less than pivot, right from that is index is
     * larger than pivot
     */
    public static int hoare_partition(int[] A, int p, int r) {
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
