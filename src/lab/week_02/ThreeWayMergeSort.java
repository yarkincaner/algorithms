package lab.week_02;

import java.util.Arrays;
import java.util.Random;

/**
 * Merge sort but splitting into three
 */
public class ThreeWayMergeSort {
    public static void main(String[] args) {
        int[] arr = generateArray(12); // Generating a random array of size n
        System.out.println("Initial Array: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    /**
     * Using start and end pointers, we won't be needing any auxiliary array
     *
     * @param A array of numbers
     * @param start pointer of starting index
     * @param end pointer of ending index
     */
    public static void mergeSort(int[] A, int start, int end) {
        if (start >= end) return; // Base case to end recursion

        int n = end - start + 1; // Size of the partial array
        int firstPartEnd = start + n / 3;
        int secondPartEnd = start + 2 * n / 3;

        mergeSort(A, start, firstPartEnd); // First part from start -> firstPartEnd
        mergeSort(A, firstPartEnd+1, secondPartEnd); // Second part from firstPartEnd+1 -> secondPartEnd
        mergeSort(A, secondPartEnd+1, end); // Third part from secondPartEnd+1 -> end

        merge(A, start, firstPartEnd, secondPartEnd, end); // Merge three sub arrays (Conquer)
    }

    public static void merge(int[] A, int start, int firstPartEnd, int secondPartEnd, int end) {
        int n1 = firstPartEnd - start + 1; // Size of first part
        int n2 = secondPartEnd - firstPartEnd; // Size of second part
        int n3 = end - secondPartEnd; // Size of third part

        // Auxiliary arrays represents part of original array
        int[] firstPart = new int[n1];
        int[] secondPart = new int[n2];
        int[] thirdPart = new int[n3];

        // Copying elements from original array to sub arrays
        for (int i = 0; i < n1; i++) {
            firstPart[i] = A[start + i];
        }
        for (int i = 0; i < n2; i++) {
            secondPart[i] = A[firstPartEnd + 1 + i];
        }
        for (int i = 0; i < n3; i++) {
            thirdPart[i] = A[secondPartEnd + 1 + i];
        }

        // Each pointer represents current index to compare with other sub arrays
        int i = 0, j = 0, k = 0;
        int key = start; // Key is the current "empty" slot on original array to be replaced with an element from sub arrays
        while (i < n1 && j < n2 && k < n3) {
            // Case the element on first sub array is smaller
            if (firstPart[i] <= secondPart[j] && firstPart[i] <= thirdPart[k]) {
                A[key] = firstPart[i];
                i++;
            }
            // Case the element on second sub array is smaller
            else if (secondPart[j] <= firstPart[i] && secondPart[j] <= thirdPart[k]) {
                A[key] = secondPart[j];
                j++;
            }
            // Case the element on third sub array is smaller
            else if (thirdPart[k] <= firstPart[i] && thirdPart[k] <= secondPart[j]) {
                A[key] = thirdPart[k];
                k++;
            }
            key++;
        }

        /*
        Since we stepped out from previous loop whenever a sub array reached its endpoint, we need to check remaining
        elements on other sub arrays.
         */
        while ((i < n1) && (j < n2)) {
            if (firstPart[i] <= secondPart[j]) {
                A[key] = firstPart[i];
                i++;
            } else {
                A[key] = secondPart[j];
                j++;
            }
            key++;
        }
        while ((j < n2) && (k < n3)) {
            if (secondPart[j] <= thirdPart[k]) {
                A[key] = secondPart[j];
                j++;
            } else {
                A[key] = thirdPart[k];
                k++;
            }
            key++;
        }
        while ((i < n1) && (k < n3)) {
            if (firstPart[i] <= thirdPart[k]) {
                A[key] = firstPart[i];
                i++;
            } else {
                A[key] = thirdPart[k];
                k++;
            }
            key++;
        }

        // Still a sub array has remaining elements, so we copy them on original array
        while (i < n1) {
            A[key] = firstPart[i];
            i++; key++;
        }
        while (j < n2) {
            A[key] = secondPart[j];
            j++; key++;
        }
        while (k < n3) {
            A[key] = thirdPart[k];
            k++; key++;
        }
    }

    public static int[] generateArray(int size) {
        Random r = new Random();
        int[] A = new int[size];

        for (int i = 0; i < size; i++) {
            A[i] = r.nextInt(30);
        }

        return A;
    }
}
