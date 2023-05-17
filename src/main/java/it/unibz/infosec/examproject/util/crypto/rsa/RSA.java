package it.unibz.infosec.examproject.util.crypto.rsa;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class RSA {

    public static RSAKeyPair generateKeys() {
        final BigInteger p = RSAUtils.generateRandomPrime();
        final BigInteger q = RSAUtils.generateRandomPrime();
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

    public static byte[] decrypt(byte[] ciphertext, BigInteger d, BigInteger n) {
        return new BigInteger(ciphertext).modPow(d, n).toByteArray();
    }
}