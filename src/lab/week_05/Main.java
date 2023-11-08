package lab.week_05;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        CTL();
        LCS();
    }

    // Cutting The Logs
    public static void CTL() {
        System.out.println("--------- Cutting The Logs ---------");

        // Initializing arrays
        int[] CTL_xsmall = generateIntArray(10, 10);
        int[] CTL_small = generateIntArray(15, 10);
        int[] CTL_medium = generateIntArray(20, 10);
        int[] CTL_large = generateIntArray(25, 10);
        int[] CTL_xlarge = generateIntArray(30, 10);


        System.out.printf("%10s | %10s\n", "Size", "Runtime (ms)");
        // Run CTL algorithm and measure runtime
        runCTL(CTL_xsmall);
        runCTL(CTL_small);
        runCTL(CTL_medium);
        runCTL(CTL_large);
        runCTL(CTL_xlarge);
    }

    public static void runCTL(int[] A) {
//        System.out.println("----- n = " + A.length + " -----");
//        System.out.println(Arrays.toString(A));
        long startTime = System.currentTimeMillis();
        int result = ctl_recursive(A, A.length);
        long endTime = System.currentTimeMillis();
        long runtime = endTime - startTime;
//        System.out.println("Result" + " --> " + result);
        System.out.printf("%10d | %10d\n", A.length, runtime);
//        System.out.println("Runtime: " + resultTime + "ms");
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

    // Longest Common Substring
    public static void LCS() {
        System.out.println("--------- Longest Common Substrings ---------");

        // Generating strings
        String X_small = generateString(10);
        String Y_small = generateString(10);
        String X_medium = generateString(15);
        String Y_medium = generateString(15);
        String X_large = generateString(20);
        String Y_large = generateString(20);

        System.out.printf("%10s | %10s\n", "Size", "Runtime (ms)");
        // Run LCS algorithm and measure runtime
        runLCS(X_small, Y_small);
        runLCS(X_medium, Y_medium);
        runLCS(X_large, Y_large);
    }

    public static void runLCS(String X, String Y) {
//        System.out.println("----- n = " + X.length() + " -----");
//        System.out.println("X: " + X + "\nY: " + Y);
        long startTime = System.currentTimeMillis();
        int result = lcs_recursive(X, Y, X.length()-1, Y.length()-1, 0);
        long endTime = System.currentTimeMillis();
        long runtime = endTime - startTime;
//        System.out.println("Result" + " --> " + result);
        System.out.printf("%10d | %10d\n", X.length(), runtime);
//        System.out.println("Runtime: " + resultTime + "ms");
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
