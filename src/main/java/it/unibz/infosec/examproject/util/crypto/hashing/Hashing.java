package it.unibz.infosec.examproject.util.crypto.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    private static final String HASH_ALGORITHM = "SHA256";
    
    public static String getDigest(String inputString) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm " + HASH_ALGORITHM);
        }
        md.update(inputString.getBytes(StandardCharsets.UTF_8));

        final StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
