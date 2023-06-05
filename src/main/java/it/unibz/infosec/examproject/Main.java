package it.unibz.infosec.examproject;

import java.math.BigInteger;
import java.util.Arrays;

import it.unibz.infosec.examproject.util.crypto.RandomUtils;
import it.unibz.infosec.examproject.util.crypto.rsa.RSA;
import it.unibz.infosec.examproject.util.crypto.rsa.RSAKeyPair;
import it.unibz.infosec.examproject.util.crypto.rsa.RSAUtils;

public class Main {
    public static void main(String[] args) {
        final RSAKeyPair k = RSA.generateKeys(1024);
        System.out.println(k);
        byte[] enc = RSA.encrypt("2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824".getBytes(), k.getPublicExponent(), k.getN());
        System.out.println(Arrays.toString(enc));
        byte[] dec = RSA.decrypt(enc, k.getPrivateExponent(), k.getN());
        System.out.println(Arrays.toString(dec));
        System.out.println(new String(dec));
    }
}
