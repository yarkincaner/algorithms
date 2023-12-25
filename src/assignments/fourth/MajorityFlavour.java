package assignments.fourth;

public class MajorityFlavour {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 5, 3, 5, 5, 3, 5, 5};
        System.out.println(findMajority(A, 0, A.length - 1));
    }

    // isMajority with O(n)
    private static boolean isMajority(int[] A, int x) {
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == x) count++;
            if (count > A.length/2) return true;
        }

        return false;
    }

    // T(n) = 2T(n/2) + O(n) Thus, makes the complexity O(nlogn)
    private static int findMajority(int[] A, int i, int j) {
        if (i >= j) return A[i];

        int middle = (i + j) / 2;
        int leftMajority = findMajority(A, i, middle);
        int rightMajority = findMajority(A, middle+1, j);

        if (isMajority(A, leftMajority)) return leftMajority;
        return rightMajority;
    }
}
