package it.unibz.infosec.examproject.util.crypto.rsa;

import java.math.BigInteger;

public class RSAKeyPair {
    private final BigInteger d;
    private final BigInteger e;
    private final BigInteger n;

    public RSAKeyPair(BigInteger n, BigInteger e, BigInteger d) {
        this.n = n;
        this.e = e;
        this.d = d;
    }

    public BigInteger getPrivateExponent() {
        return d;
    }

    public BigInteger getPublicExponent() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }

    @Override
    public String toString() {
        return "RSAKeyPair(n = " + this.n.toString() + ", e = " + this.e.toString() + ", d = " + this.d.toString() + ")";
    }
}