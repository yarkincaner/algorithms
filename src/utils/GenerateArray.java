package utils;

import java.util.Random;

public class GenerateArray {
    private static Random r = new Random();

    public static int[] generateIntArray(int size) {
        int[] A = new int[size];

        for (int i = 0; i < size; i++) {
            A[i] = r.nextInt(size*3);
        }

        return A;
    }
}
