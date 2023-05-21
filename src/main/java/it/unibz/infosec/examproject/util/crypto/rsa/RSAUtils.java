package it.unibz.infosec.examproject.util.crypto.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAUtils {

    public static BigInteger[] eea(final BigInteger a, final BigInteger b) {
        if (BigInteger.ZERO.equals(b)) {
            return new BigInteger[] { a, BigInteger.ONE, BigInteger.ZERO };
        }

        final BigInteger[] eea = eea(b, a.mod(b));
        final BigInteger gcd = eea[0], x1 = eea[1], x = eea[2];
        final BigInteger y = x1.subtract(a.divideAndRemainder(b)[0].multiply(x));
        return new BigInteger[] { gcd, x, y };
    }

    /**
     * Calculate private exponent d for RSA key generation, given public exponent e and
     * coefficient phi = (p - 1) * (q - 1), coprime numbers.
     * This makes use of the Extended Euclidean Algorithm.
     *
     * @see #eea(BigInteger, BigInteger) for details on the implementation of the Extended
     * Euclidean Algorithm
     * @return the calculated private exponent d, such that ed = 1 % phi
     * @throws IllegalArgumentException if provided e and phi are not coprime (eea cannot be applied)
     *
     * */
    public static BigInteger d(BigInteger e, BigInteger phi) {
        final BigInteger[] eea = eea(e, phi);
        final BigInteger gcd = eea[0], x = eea[1];
        if (BigInteger.ONE.equals(gcd)) {
            return x.mod(phi);
        }
        throw new IllegalArgumentException("e and phi must be coprime");
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger t;
        while (!BigInteger.ZERO.equals(b)) {
            t = b;
            b = a.mod(b);
            a = t;
        }
        return a;
    }

    public static BigInteger generateRandomPrime() {
        final SecureRandom rnd = new SecureRandom();
        int n;
        do {
            n = rnd.nextInt(Integer.MAX_VALUE - 1) + 1;
        } while (!isPrime(n));
        return BigInteger.valueOf(n);
    }

    public static boolean isPrime(int n) {
        if (n <= 3 || n % 2 == 0) {
            return n == 2 || n == 3;
        }
        int d = 3;
        while ((d <= Math.sqrt(n)) && n % d != 0) {
            d += 2;
        }
        return n % d != 0;
    }

}
