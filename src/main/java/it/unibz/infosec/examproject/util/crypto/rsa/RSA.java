package it.unibz.infosec.examproject.util.crypto.rsa;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class RSA {

    public static RSAKeyPair generateKeys(int bits) {
        final BigInteger p = RSAUtils.generateRandomPrime(bits);
        final BigInteger q = RSAUtils.generateRandomPrime(bits);
        final BigInteger n = p.multiply(q);
        final BigInteger phi = p.subtract(BigInteger.ONE)
            .multiply(q.subtract(BigInteger.ONE));

        BigInteger e;
        for (e = BigInteger.TWO; phi.compareTo(e) > 0; e = e.add(BigInteger.ONE)) {
            if (BigInteger.ONE.equals(RSAUtils.gcd(e, phi))) break;
        }

        return new RSAKeyPair(n, e, RSAUtils.d(e, phi));
    }

    public static byte[] encrypt(byte[] cleartext, BigInteger e, BigInteger n) {
        return new BigInteger(cleartext).modPow(e, n).toByteArray();
    }

    public static byte[] encrypt(String payload, BigInteger e, BigInteger n) {
        return encrypt(payload.getBytes(StandardCharsets.UTF_8), e, n);
    }

    public static byte[] decrypt(byte[] ciphertext, BigInteger d, BigInteger n) {
        return new BigInteger(ciphertext).modPow(d, n).toByteArray();
    }

    public static String decryptToString(byte[] ciphertext, BigInteger d, BigInteger n) {
        return new String(decrypt(ciphertext, d, n), StandardCharsets.UTF_8);
    }
}