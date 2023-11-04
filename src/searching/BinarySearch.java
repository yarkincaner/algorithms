package searching;

/**
 * Binary Search is a searching algorithm that checks middle of the array. If the value in middle
 * bigger than the searched value, it jumps to middle index of the first half of the array; it the
 * value in middle lower than the searched value, it jumps to middle index of second half.
 * At each recursion array size is decreased by half, thus decreases the complexity by far.
 *
 * Only works when the array is sorted.
 *
 * Worst case: O(logn)
 * Best case: O(1)
 */
public class BinarySearch {
    public static int searchInt(int[] A, int start, int end, int value) {
        if (start > end) return -1; // Value not exist in array A
        int middle = start + Math.floorDiv((end - start), 2);
        if (A[middle] == value) return middle;
        if (A[middle] < value) return  searchInt(A, middle+1, end, value);
        if (A[middle] > value) return searchInt(A, start, middle-1, value);
        return -1;
    }
}
