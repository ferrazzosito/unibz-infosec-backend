package it.unibz.infosec.examproject.util.crypto.rsa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class RSATest {

    @Test
    public void testGeneratedKeysAreRandom() {
        RSAKeyPair keyPair1 = RSA.generateKeys(1024);
        RSAKeyPair keyPair2 = RSA.generateKeys(1024);
        Assertions.assertNotEquals(keyPair1.getN(), keyPair2.getN());
    }

    @Test
    public void testEncryptionAndDecryption() {
        final String payload = "hello";
        final RSAKeyPair keyPair = RSA.generateKeys(1024);
        Assertions.assertEquals(payload,
                RSA.decryptToString(
                RSA.encrypt(
                        payload,
                        keyPair.getPublicExponent(),
                        keyPair.getN()
                ),
                keyPair.getPrivateExponent(),
                keyPair.getN()
        ));
    }

}
