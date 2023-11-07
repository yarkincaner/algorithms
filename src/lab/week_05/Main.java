package lab.week_05;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        CTL();
        LCS();
    }

    // Cutting the logs
    public static void CTL() {
        System.out.println("--------- Cutting The Logs ---------");
        int[] CTL10 = generateIntArray(10, 10);
//        int[] CTL100 = generateIntArray(100, 10);
//        int[] CTL1000 = generateIntArray(1000, 10);
//        int[] CTL10000 = generateIntArray(10000, 10);

        System.out.println(Arrays.toString(CTL10));
        long startTime10 = System.currentTimeMillis();
        System.out.println("Result of n = 4: " + ctl_recursive(CTL10, 4));
        long endTime10 = System.currentTimeMillis();
        long resultTime10 = endTime10 - startTime10;
        System.out.println("Runtime: " + resultTime10 + "ms");
    }

    /**
     *
     * @param A array of prices. Each index+1 indicates length of the piece.
     * @param n maximum size that can be cut out.
     * @return r_n maximum revenue obtained by cutting up the rod
     */
    public static int ctl_recursive(int[] A, int n) {
        if (n == 0) return 0; // Base case to end recursive calls
        int r_n = 0;
        for (int i = 1; i <= n; i++) {
            r_n = Math.max(r_n, A[i-1] + ctl_recursive(A, n-i));
        }
        return r_n;
    }

    public static void LCS() {
        System.out.println("--------- Longest Common Substrings ---------");

        // Generating strings
        String X10 = generateString(10);
        String Y10 = generateString(10);
        String X100 = generateString(100);
        String Y100 = generateString(100);
        String X1000 = generateString(1000);
        String Y1000 = generateString(1000);
        String X10000 = generateString(10000);
        String Y10000 = generateString(10000);
        // n = 10
        long startTime10 = System.currentTimeMillis();
        int result10 = lcs_recursive(X10, Y10, X10.length()-1, Y10.length()-1, 0);
        long endTime10 = System.currentTimeMillis();
        long runningTime10 = endTime10 - startTime10;
        System.out.println("Size n = 10: " + result10 + "\nRuntime: " + runningTime10 + "ms");
        // n = 100
//        long startTime100 = System.currentTimeMillis();
//        int result100 = lcs_recursive(X100, Y100, X100.length()-1, Y100.length()-1, 0);
//        long endTime100 = System.currentTimeMillis();
//        long runningTime100 = endTime100 - startTime100;
//        System.out.println("Result of size 100: " + result100 + " in " + runningTime100 + "ms");
//        // n = 1000
//        long startTime1000 = System.currentTimeMillis();
//        int result1000 = lcs_recursive(X1000, Y1000, X1000.length()-1, Y1000.length()-1, 0);
//        long endTime1000 = System.currentTimeMillis();
//        long runningTime1000 = endTime1000 - startTime1000;
//        System.out.println("Result of size 1000: " + result1000 + " in " + runningTime1000 + "ms");
//        // n = 10000
//        long startTime10000 = System.currentTimeMillis();
//        int result10000 = lcs_recursive(X10000, Y10000, X10000.length()-1, Y10000.length()-1, 0);
//        long endTime10000 = System.currentTimeMillis();
//        long runningTime10000 = endTime10000 - startTime10000;
//        System.out.println("Result of size 10000: " + result10000 + " in " + runningTime10000 + "ms");
    }

    /**
     *
     * @param X first string
     * @param Y second string
     * @param m pointer of the last character of X
     * @param n pointer of the last character of Y
     * @param lcsSize largest common substring size
     * @return lcsSize
     */
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
     * Generate random integer array in ascending order. First index is always 1.
     * @param size array size
     * @param incrRate Incremental rate for each price.
     * @return Randomly generated array
     */
    public static int[] generateIntArray(int size, int incrRate) {
        int[] A = new int[size];
        A[0] = 1; // Start array with value 1
        Random r = new Random();
        for (int i = 1; i < size; i++) {
            A[i] = r.nextInt(incrRate) + A[i-1];
        }
        return A;
    }

    /**
     * Generates a string with randomly selected letters from the alphabet of given size
     * @param size size of the string to be created
     * @return s generated string
     */
    public static String generateString(int size) {
        Random r = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return s;
    }
}
