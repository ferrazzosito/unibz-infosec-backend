package it.unibz.infosec.examproject.util.crypto.des;

import it.unibz.infosec.examproject.util.crypto.des.BlockEncoder;
import it.unibz.infosec.examproject.util.crypto.des.DES;
import it.unibz.infosec.examproject.util.crypto.des.SubKeysGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DesTest {

    @Test
    public void testSubKeysGeneration () {
        String startingKey = "0001001100110100010101110111100110011011101111001101111111110001";
        String[] subkeys = SubKeysGenerator.generateSubKeys(startingKey);

        assertEquals("000110110000001011101111111111000111000001110010", subkeys[0]);
        assertEquals("011110011010111011011001110110111100100111100101", subkeys[1]);
        assertEquals("010101011111110010001010010000101100111110011001", subkeys[2]);
        assertEquals("011100101010110111010110110110110011010100011101", subkeys[3]);
        assertEquals("011111001110110000000111111010110101001110101000", subkeys[4]);
        assertEquals("011000111010010100111110010100000111101100101111", subkeys[5]);
        assertEquals("111011001000010010110111111101100001100010111100", subkeys[6]);
        assertEquals("111101111000101000111010110000010011101111111011", subkeys[7]);
        assertEquals("111000001101101111101011111011011110011110000001", subkeys[8]);
        assertEquals("101100011111001101000111101110100100011001001111", subkeys[9]);
        assertEquals("001000010101111111010011110111101101001110000110", subkeys[10]);
        assertEquals("011101010111000111110101100101000110011111101001", subkeys[11]);
        assertEquals("100101111100010111010001111110101011101001000001", subkeys[12]);
        assertEquals("010111110100001110110111111100101110011100111010", subkeys[13]);
        assertEquals("101111111001000110001101001111010011111100001010", subkeys[14]);
        assertEquals("110010110011110110001011000011100001011111110101", subkeys[15]);

    }

    @Test
    public void testBlockEncoder () {
        String input = "0000000100100011010001010110011110001001101010111100110111101111";

        assertEquals("1100110000000000110011001111111111110000101010101111000010101010", BlockEncoder.encodeBlock(input));
    }

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
