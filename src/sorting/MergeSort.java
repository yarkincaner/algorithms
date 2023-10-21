package sorting;

/**
 * Merge Sort is a divide and conquer algorithm. It divides array into two parts recursively (depth-first approach).
 * At one point it reaches to the leaf and starts to merge siblings meanwhile compares them.
 *
 * Complexity of Merge Sort is always O(nlogn) because each recursive call is actually a tree node. There are no worst
 * or best case. Input size or structure does not affect the algorithm.
 */
public class MergeSort {
    public static void sortInt(int[] A, int start, int end) {
        if (start == end) return;

        int middle = Math.floorDiv(start + end, 2);
        sortInt(A, start, middle);
        sortInt(A, middle+1, end);

        merge(A, start, middle, end);
    }

    private static void merge(int[] A, int start, int middle, int end) {
        int n1 = middle - start + 1;
        int n2 = end - middle;

        int[] firstHalf = new int[n1];
        int[] secondHalf = new int[n2];

        for (int i = 0; i < n1; i++) {
            firstHalf[i] = A[start + i];
        }
        for (int i = 0; i < n2; i++) {
            secondHalf[i] = A[middle + 1 + i];
        }

        int i = 0, j = 0;
        int k = start;
        while (i < n1 && j < n2) {
            if (firstHalf[i] <= secondHalf[j]) {
                A[k] = firstHalf[i];
                i++;
            } else {
                A[k] = secondHalf[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            A[k] = firstHalf[i];
            i++; k++;
        }

        while (j < n2) {
            A[k] = secondHalf[j];
            j++; k++;
        }
    }
}
