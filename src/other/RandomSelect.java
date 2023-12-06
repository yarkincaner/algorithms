package other;

import sorting.RandomizedQuickSort;

public class RandomSelect {
    /**
     * Find ith smallest element in an array
     * @param A
     * @param p
     * @param r
     * @param i
     * @return
     */
    public static int random_select(int[] A, int p, int r, int i) {
        if (p == r) return A[p];
        int q = RandomizedQuickSort.partition(A, p, r);
        int k = q - p + 1;
        if (i <= k) return random_select(A, p, q, i);
        return random_select(A, q+1, r, i-k);
    }
}
