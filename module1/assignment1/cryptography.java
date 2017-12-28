
/**
 * Write a description of cryptography here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class cryptography {
    public String basicEncryption(String message, int key) {
        // transform message to encryptable data;
        message = message.toUpperCase();
        // make a string builder with message
        StringBuilder encrypted = new StringBuilder(message);
        // write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // write down the encryption code
        String encryption = alphabet.substring(key) + alphabet.substring(0, key);
        // for loop starting at 0 and ending at encrypted.length()
        for (int i = 0; i < encrypted.length(); i++) {
            // look at the ith character (currChar)
            char currChar = encrypted.charAt(i);
            // find the index of currChar in the alphabet
            int currIndex = alphabet.indexOf(currChar);
            // if currChar is in the alphabet
            if (currIndex != -1) {
                // get the index character of the encryption
                char newChar = encryption.charAt(currIndex);
                // replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testBasicEnc() {
        int key = 15;
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = basicEncryption(message, key);
        System.out.println(encrypted);
        String unencrypted = basicEncryption(encrypted, 26 - key);
        System.out.println(unencrypted);
    }
}
