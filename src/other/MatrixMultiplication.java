package other;

public class MatrixMultiplication {
    /**
     * Traditional matrix multiplication process
     * Complexity: O(n^3)
     * @param A first matrix
     * @param B second matrix
     * @return C result matrix
     */
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowA = A[0].length;
        int colB = B.length;
        int rowB = B[0].length;
        int C[][] = new int[rowA][colB];

        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                for (int k = 0; k < rowB; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    /**
     * Traditional matrix multiplication with divide and conquer approach.
     * This only works for 2x2 and 3x3 matrices.
     *
     * T(n) = 8T(n/2) + O(n^2)
     * In master theorem, complexity is: O(n^3)
     *
     * @param A first matrix
     * @param B second matrix
     * @param rowA row index of A
     * @param colA column index of A
     * @param rowB row index of B
     * @param colB column index of B
     * @param size size of result matrix
     * @return C result matrix
     */
    public static int[][] multiplyDAC(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB, int size) {
        int[][] C = new int[size][size];
        if (size == 1) {
            C[0][0] = A[rowA][colA] * B[rowB][colB];
        } else if (size == 2){
            int newSize = size / 2;
            // C11 = A11 * B11 + A12 * B21
            int[][] C11 = sumMatrix(
                    multiplyDAC(A, B, rowA, colA, rowB, colB, newSize),
                    multiplyDAC(A, B, rowA, colA + newSize, rowB + newSize, colB, newSize)
            );
            int[][] C12 = sumMatrix(
                    multiplyDAC(A, B, rowA, colA, rowB, colB + newSize, newSize),
                    multiplyDAC(A, B, rowA, colA + newSize, rowB + newSize, colB + newSize, newSize)
            );
            int[][] C21 = sumMatrix(
                    multiplyDAC(A, B, rowA + newSize, colA, rowB, colB, newSize),
                    multiplyDAC(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB, newSize)
            );
            int[][] C22 = sumMatrix(
                    multiplyDAC(A, B, rowA + newSize, colA, rowB, colB + newSize, newSize),
                    multiplyDAC(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB + newSize, newSize)
            );

            // Combine the submatrices to form the result
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = C11[i][j];
                    C[i][j + newSize] = C12[i][j];
                    C[i + newSize][j] = C21[i][j];
                    C[i + newSize][j + newSize] = C22[i][j];
                }
            }
        } else {
            // For 3x3 matrices, apply standard matrix multiplication
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    C[i][j] = 0;
                    for (int k = 0; k < 3; k++) {
                        C[i][j] += A[rowA + i][colA + k] * B[rowB + k][colB + j];
                    }
                }
            }
        }

        return C;
    }

    private static int[][] sumMatrix(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }
}
