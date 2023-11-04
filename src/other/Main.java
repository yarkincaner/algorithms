package other;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int randomNum = r.nextInt(10) + 1;
        int randomPow = r.nextInt(10) + 1;
        System.out.println(randomNum + "^" + randomPow + " = " + Pow.power(randomNum, randomPow));
    }
}
