package it.unibz.infosec.examproject.util.crypto.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    
    public static String getDigest(String inputString, String hashAlgorithm) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance(hashAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm " + hashAlgorithm);
        }
        md.update(inputString.getBytes(StandardCharsets.UTF_8));

        final StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
