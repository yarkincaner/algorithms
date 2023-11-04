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

    public static int[][] generate2DIntArray(int row, int col) {
        int[][] A = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                A[i][j] = r.nextInt(row * col * 3);
            }
        }

        return A;
    }
}
