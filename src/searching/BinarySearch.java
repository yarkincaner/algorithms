package searching;

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
