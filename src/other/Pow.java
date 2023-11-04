package other;

public class Pow {
    public static int power(int a, int n) {
        if (n == 0) return 1;
        int value;

        // if n is even
        if ((n % 2) == 0) {
            value = power(a, n/2);
            return value * value;
        }

        // if n is odd
        value = power(a, (n-1)/2);
        return value * value * a;
    }
}
