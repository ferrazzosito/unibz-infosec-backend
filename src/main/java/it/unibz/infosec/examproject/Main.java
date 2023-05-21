package it.unibz.infosec.examproject;

import java.math.BigInteger;
import java.util.Arrays;

import it.unibz.infosec.examproject.util.crypto.rsa.RSA;
import it.unibz.infosec.examproject.util.crypto.rsa.RSAKeyPair;

public class Main {
    public static void main(String[] args) {
        final RSAKeyPair k = RSA.generateKeys();
        System.out.println(k);
        byte[] enc = RSA.encrypt("hello".getBytes(), k.getPublicExponent(), k.getN());
        System.out.println(Arrays.toString(enc));
        byte[] dec = RSA.decrypt(enc, k.getPrivateExponent(), k.getN());
        System.out.println(Arrays.toString(dec));
        System.out.println(new String(dec));
    }
}
