package lab.week_midterm;

import utils.GenerateArray;

import java.util.Arrays;

/**
 * Write an algorithm that returns two elements from an n size of an integer array.
 * The subtraction of these two elements results in the maximum value from any other
 * pair in the array. This algorithm should be a divide-and-conquer algorithm with
 * O(n) time complexity and recursive structure. For example:
 * 𝐴 = [4. 5, 10, − 2, π, − 7. 115]
 * >> 17.115.
 * En büyük ve en küçüğü bulup döndür sonra farklarını al
 */
public class Main {
    static class Result {
        int min;
        int max;

        public Result(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
    public static void main(String[] args) {
        int[] A = GenerateArray.generateIntArray(9);
        System.out.println(Arrays.toString(A));
        System.out.println(findMaxSubRecursively(A));
    }

    public static int findMaxSubRecursively(int[] A) {
        Result result = findMinAndMax(A, 0, A.length-1);
        return result.max-result.min;
    }

    public static Result findMinAndMax(int[] A, int start, int end) {
        if (start == end) return new Result(A[start], A[start]);

        if (end == start+1) {
            if (A[start] > A[end]) return new Result(A[end], A[start]);
            else return new Result(A[start], A[end]);
        }

        int middle = Math.floorDiv(start+end, 2);
        Result leftSub = findMinAndMax(A, start, middle);
        Result rightSub = findMinAndMax(A, middle+1, end);

        int max = Math.max(leftSub.max, rightSub.max);
        int min = Math.min(leftSub.min, rightSub.min);

        return new Result(min, max);
    }
}
