package it.unibz.infosec.examproject.util.crypto;

import java.math.BigInteger;
import java.util.Arrays;

public class RandomUtils {

    public static final char[] saltAlphabet =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static void lcg(int seed, int mod, int mul, int inc, int[] rnd, int n) {
        BigInteger tmp;
        rnd[0] = seed;
        for (int i = 1; i < n; i++) {
            tmp = BigInteger.valueOf(rnd[i - 1]);
            rnd[i] = tmp.multiply(BigInteger.valueOf(mul))
                    .add(BigInteger.valueOf(inc))
                    .mod(BigInteger.valueOf(mod))
                    .intValue();
        }
    }

    public static int[] generateRandomIntegers(int seed, int max, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("random numbers count n must be a positive non-zero quantity");
        }
        final int[] rnd = new int[n + 1];
        final long now = System.nanoTime();
        lcg(seed, max, (int) (now & 0xFF), (int) ((now >> 8) & 0xFF), rnd, n + 1);
        return Arrays.copyOfRange(rnd, 1, n + 2);
    }

    public static int generateRandomInteger(int seed, int max) {
        return generateRandomIntegers(seed, max, 1)[0];
    }

    public static String generateRandomSalt(int length) {
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = generateRandomInteger(
                    (int) System.currentTimeMillis(), saltAlphabet.length - 1);
            sb.append(saltAlphabet[index]);
        }
        return sb.toString();
    }

}
