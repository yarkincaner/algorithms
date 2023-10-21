import java.util.Arrays;

public class Deneme {

    // Main mergeSort function that divides the array into three parts
    public static void mergeSort(int[] arr, int left, int right) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        if (left < right) {
            int n = right - left + 1;
            int mid1 = left + n / 3;
            int mid2 = left + 2 * n / 3;

            // Recursively sort each sub-array
            mergeSort(arr, left, mid1);
            mergeSort(arr, mid1 + 1, mid2);
            mergeSort(arr, mid2 + 1, right);

            // Merge the sorted sub-arrays in-place
            merge(arr, left, mid1, mid2, right);
        }
    }

    // Merge three sub-arrays into one sorted array in-place
    public static void merge(int[] arr, int left, int mid1, int mid2, int right) {
        int n1 = mid1 - left + 1;
        int n2 = mid2 - mid1;
        int n3 = right - mid2;

        int[] temp1 = new int[n1];
        int[] temp2 = new int[n2];
        int[] temp3 = new int[n3];

        // Copy elements to temporary arrays
        for (int i = 0; i < n1; i++) {
            temp1[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            temp2[i] = arr[mid1 + 1 + i];
        }
        for (int i = 0; i < n3; i++) {
            temp3[i] = arr[mid2 + 1 + i];
        }

        int i = 0, j = 0, k = 0, l = left;

        // Merge the three sub-arrays into the original array
        while (i < n1 && j < n2 && k < n3) {
            if (temp1[i] <= temp2[j] && temp1[i] <= temp3[k]) {
                arr[l++] = temp1[i++];
            } else if (temp2[j] <= temp1[i] && temp2[j] <= temp3[k]) {
                arr[l++] = temp2[j++];
            } else {
                arr[l++] = temp3[k++];
            }
        }

        // Copy any remaining elements from the temporary arrays
        while (i < n1) {
            arr[l++] = temp1[i++];
        }
        while (j < n2) {
            arr[l++] = temp2[j++];
        }
        while (k < n3) {
            arr[l++] = temp3[k++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7, 15, 8, 9};
        System.out.println("Original Array: " + Arrays.toString(arr));

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
}

