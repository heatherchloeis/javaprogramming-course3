
/**
 * Write a description of caesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class caesarCipher {
    public String caesarCipher(String phrase, int key1, int key2) {
        // transform message to encryptable data;
        phrase = phrase.toUpperCase();
        // make a string builder with message
        StringBuilder encrypted = new StringBuilder(phrase);
        // write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // for loop starting at 0 and ending at encrypted.length()
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                // write down the encryption code
                String encryption = alphabet.substring(key1) + alphabet.substring(0, key1);
                // look at the ith character (currChar)
                char currChar = encrypted.charAt(i);
                // find the index of currChar in the alphabet
                int currIndex = alphabet.indexOf(currChar);
                // if currChar is in the alphabet
                if (currIndex != -1) {
                    char newChar = encryption.charAt(currIndex);
                    encrypted.setCharAt(i, newChar);
                }
            }
            if (i % 2 != 0) {
                String encryption = alphabet.substring(key2) + alphabet.substring(0, key2);
                char currChar2 = encrypted.charAt(i);
                int currIndex2 = alphabet.indexOf(currChar2);
                if (currIndex2 != -1) {
                    // get the index character of the encryption
                    char newChar2 = encryption.charAt(currIndex2);
                    // replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar2);
                }
            }
        }
        return encrypted.toString();
    }
    public void testCaesarCipher() {
        int key1 = 8;
        int key2 = 21;
        String phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(phrase);
        String encrypted = caesarCipher(phrase, key1, key2);
        System.out.println(encrypted);
        String unencrypted = caesarCipher(phrase, 26 - key1, 26-key2);
        System.out.println(unencrypted);
    }
}
