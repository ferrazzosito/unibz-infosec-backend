package it.unibz.infosec.examproject.utils.des;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesTest {

    @Test
    public void testDESEncryption () {
        String plaintext = "0000000100100011010001010110011110001001101010111100110111101111";
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String ciphertext = DES.encodeMessage(plaintext, key);

        assertEquals("1000010111101000000100110101010000001111000010101011010000000101", ciphertext);
    }

    @Test
    public void testDESDecryption () {
        String ciphertext = "1000010111101000000100110101010000001111000010101011010000000101";
        String key = "0001001100110100010101110111100110011011101111001101111111110001";
        String plaintext = DES.decodeMessage(ciphertext, key);

        assertEquals("0000000100100011010001010110011110001001101010111100110111101111", plaintext);
    }

    @Test
    public void testDESDecryption2 () {
        String plaintext = "1000010111101000000100000101010000001111000010101011010000000101";
        String key = "0010101010000101010011000000010101001010010001010101001010101010001011110";
        String ciphertext = DES.encodeMessage(plaintext, key);

        String plaintext2 = DES.decodeMessage(ciphertext, key);

        assertEquals(plaintext, plaintext2);
    }
}
