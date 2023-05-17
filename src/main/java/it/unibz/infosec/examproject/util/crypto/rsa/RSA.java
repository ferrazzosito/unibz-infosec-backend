package it.unibz.infosec.examproject.util.crypto.rsa;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class RSA {

    public static BigInteger[] eea(final BigInteger a, final BigInteger b) {
        if (BigInteger.ZERO.equals(b)) {
            return new BigInteger[] { a, BigInteger.ONE, BigInteger.ZERO };
        }

        final BigInteger[] eea = eea(b, a.mod(b));
        final BigInteger gcd = eea[0], x1 = eea[1], x = eea[2];
        final BigInteger y = x1.subtract(a.divideAndRemainder(b)[0].multiply(x));
        return new BigInteger[] { gcd, x, y };
    }

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

    private static boolean isPrime(int n) {
        if (n <= 3 || n % 2 == 0) {
            return n == 2 || n == 3;
        }
        int d = 3;
        while ((d <= Math.sqrt(n)) && n % d != 0) {
            d += 2;
        }
        return n % d != 0;
    }

    public static RSAKeyPair generateKeys() {
        final BigInteger p = generateRandomPrime();
        final BigInteger q = generateRandomPrime();
        final BigInteger n = p.multiply(q);
        final BigInteger phi = p.subtract(BigInteger.ONE)
            .multiply(q.subtract(BigInteger.ONE));

        BigInteger e;
        for (e = BigInteger.TWO; phi.compareTo(e) > 0; e = e.add(BigInteger.ONE)) {
            if (BigInteger.ONE.equals(gcd(e, phi))) break;
        }

        return new RSAKeyPair(n, e, d(e, phi));
    }

    public static byte[] encrypt(byte[] cleartext, BigInteger e, BigInteger n) {
        return new BigInteger(cleartext).modPow(e, n).toByteArray();
    }

    public static byte[] decrypt(byte[] ciphertext, BigInteger d, BigInteger n) {
        return new BigInteger(ciphertext).modPow(d, n).toByteArray();
    }
}