package searching;

import utils.GenerateArray;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = GenerateArray.generateIntArray(597);
//        System.out.println("Initial array: " + Arrays.toString(arr));
        Arrays.sort(arr);
//        System.out.println("Sorted array: " + Arrays.toString(arr));
        Random r = new Random();
        int randomIndex = r.nextInt(arr.length);
        System.out.println("Selected index: " + randomIndex);
        System.out.println("Found index: " + BinarySearch.searchInt(arr, 0, arr.length, arr[randomIndex]));
    }
}
