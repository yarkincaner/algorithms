package lab.week_05;

import java.util.Random;

public class Main {
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        LCS();
    }

    public static void CTL() {
        System.out.println("--------- Cutting The Logs ---------");
//        int[] CTL10 = generateIntArray(10);
//        int[] CTL100 = generateIntArray(100);
//        int[] CTL1000 = generateIntArray(1000);
//        int[] CTL10000 = generateIntArray(10000);
    }

    public static void LCS() {
        System.out.println("--------- Longest Common Substrings ---------");
        String X = generateString(10);
        String Y = generateString(10);
        System.out.println("X: " + X + "\nY: " + Y);
        long startTime = System.currentTimeMillis();
        int result = lcs_recursive(X, Y, X.length()-1, Y.length()-1, 0);
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("Result: " + result + " in " + runningTime + "ms");
    }

    public static int lcs_recursive(String X, String Y, int m, int n, int lcsSize) {
        if (m == -1 || n == -1) return lcsSize;
        if (X.charAt(m) == Y.charAt(n)) {
            lcsSize = lcs_recursive(X, Y, m-1, n-1, ++lcsSize);
        } else {
            lcsSize = Math.max(lcsSize, Math.max(lcs_recursive(X, Y, m, n-1, 0), lcs_recursive(X, Y, m-1, n, 0)));
        }
        return lcsSize;
    }

    /**
     * Generate random integer random
     * @param size array size
     * @return Randomly generated array
     */
    public static int[] generateIntArray(int size) {
        int[] A = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            A[i] = r.nextInt(size*3) + 1;
        }
        return A;
    }

    public static String generateString(int size) {
        Random r = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return s;
    }
}
