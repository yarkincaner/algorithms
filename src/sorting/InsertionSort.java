package sorting;

/**
 * Row  Code                                cost    times
 * 1    for j <- 2 to n do                  c1      n
 * 2        key <- A[j]                     c2      n - 1
 * 3        i <- j-1                        c3      n - 1
 * 4        while i > 0 and A[i] > key do   c4      sum j=2 to n (tj)
 * 5            A[i+1] <- A[i]              c5      sum j=2 to n (tj - 1)
 * 6            i <- i-1                    c6      sum j=2 to n (tj - 1)
 * 7        end while                       0
 * 8        A[i+1] <- key                   c7      n - 1
 * 9    end for                             0
 *
 * Worst case:  If given array is reversely sorted O(n^2).
 * Best case:   If given array is already sorted O(n).
 */

public class InsertionSort {
    public static void sortInt(int[] A) {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;

            while (i >= 0 && A[i] > key) {
                A[i+1] = A[i];
                i--;
            }

            A[i+1] = key;
        }
    }
}
