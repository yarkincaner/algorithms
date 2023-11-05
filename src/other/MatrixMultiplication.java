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
            int[][] C11 = add(
                    multiplyDAC(A, B, rowA, colA, rowB, colB, newSize),
                    multiplyDAC(A, B, rowA, colA + newSize, rowB + newSize, colB, newSize)
            );
            int[][] C12 = add(
                    multiplyDAC(A, B, rowA, colA, rowB, colB + newSize, newSize),
                    multiplyDAC(A, B, rowA, colA + newSize, rowB + newSize, colB + newSize, newSize)
            );
            int[][] C21 = add(
                    multiplyDAC(A, B, rowA + newSize, colA, rowB, colB, newSize),
                    multiplyDAC(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB, newSize)
            );
            int[][] C22 = add(
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

    /**
     * Strassen's algorithm that decreases the complexity of matrix multiplication with tricky
     * operations. Decreases the number of subproblems from 8 to 7.
     *
     * T(n) = 7T(n/2) + O(n^2) Thus,
     * Complexity ≈ O(n^2.81)
     *
     * @param A
     * @param B
     * @return
     */
    public static int[][] strassen(int[][] A, int[][] B) {
        int size = A.length;
        int[][] C = new int[size][size];
        if (size == 1) C[0][0] = A[0][0] * B[0][0];
        else {
            // Step 1: Dividing Matrix into parts
            int[][] A11 = new int[size/2][size/2];
            int[][] A12 = new int[size/2][size/2];
            int[][] A21 = new int[size/2][size/2];
            int[][] A22 = new int[size/2][size/2];
            int[][] B11 = new int[size/2][size/2];
            int[][] B12 = new int[size/2][size/2];
            int[][] B21 = new int[size/2][size/2];
            int[][] B22 = new int[size/2][size/2];

            // Step 2: Dividing matrix A into 4 halves
            split(A, A11, 0, 0);
            split(A, A12, 0, size / 2);
            split(A, A21, size / 2, 0);
            split(A, A22, size / 2, size / 2);

            // Step 2: Dividing matrix B into 4 halves
            split(B, B11, 0, 0);
            split(B, B12, 0, size / 2);
            split(B, B21, size / 2, 0);
            split(B, B22, size / 2, size / 2);

            // P1 = A11 * (B12 - B22)
            int[][] P1 = strassen(A11, sub(B12, B22));
            // P2 = (A11 + A12) * B22
            int[][] P2 = strassen(add(A11, A12), B22);
            // P3 = (A21 + A22) * B11
            int[][] P3 = strassen(add(A21, A22), B11);
            // P4 = A22 * (B21 - B11)
            int[][] P4 = strassen(A22, sub(B21, B11));
            // P5 = (A11 + A22) * (B11 + B22)
            int[][] P5 = strassen(add(A11, A22), add(B11, B22));
            // P6 = (A12 - A22) * (B21 + B22)
            int[][] P6 = strassen(sub(A12, A22), add(B21, B22));
            // P7 = (A11 - A21) * (B11 + B12)
            int[][] P7 = strassen(sub(A11, A21), add(B11, B12));

            // C11 = P5 + P4 - P2 + P6
            int[][] C11 = add(sub(add(P5, P4), P2), P6);
            // C12 = P1 + P2
            int[][] C12 = add(P1, P2);
            // C21 = P3 + P4
            int[][] C21 = add(P3, P4);
            // C22 = P5 + P1 - P3 -P7
            int[][] C22 = sub(sub(add(P5, P1), P3), P7);

            // Merge 4 halves into one result matrix
            merge(C11, C, 0, 0);
            merge(C12, C, 0, size/2);
            merge(C21, C, size/2, 0);
            merge(C22, C, size/2, size/2);
        }

        return C;
    }

    /**
     * Merges child matrices into parent matrix
     * For detailed info: https://www.geeksforgeeks.org/implementing-strassens-algorithm-in-java/
     * @param C child
     * @param P parent
     * @param iB
     * @param jB
     */
    private static void merge(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }

    /**
     * Splits parent matrix into child matrices
     * For detailed info: https://www.geeksforgeeks.org/implementing-strassens-algorithm-in-java/
     * @param P parent matrix
     * @param C child matrix
     * @param iB
     * @param jB
     */
    private static void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    private static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    private static int[][] add(int[][] A, int[][] B) {
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
