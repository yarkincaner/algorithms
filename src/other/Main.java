package other;

import utils.GenerateArray;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        matrixMultiplication();
    }

    public static void calculatePower() {
        Random r = new Random();
        int randomNum = r.nextInt(10) + 1;
        int randomPow = r.nextInt(10) + 1;
        System.out.println(randomNum + "^" + randomPow + " = " + Pow.power(randomNum, randomPow));
    }

    public static void matrixMultiplication() {
        int[][] A = GenerateArray.generate2DIntArray(2, 2);
        int[][] B = GenerateArray.generate2DIntArray(2, 2);

        System.out.println("A: " + Arrays.deepToString(A));
        System.out.println("B: " + Arrays.deepToString(B));

//        System.out.println(Arrays.deepToString(MatrixMultiplication.multiply(A, B)));
        System.out.println("C: " + Arrays.deepToString(MatrixMultiplication.multiplyDAC(A, B, 0, 0, 0, 0, A.length)));
    }
}
