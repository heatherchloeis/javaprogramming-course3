
/**
 * Write a description of decryptCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.Random;

public class decryptCaesarCipher {
    public int countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int idx = alphabet.indexOf(ch);
            if (idx != -1) {
                count[idx] += 1;
            }
        }
        return maxIndex(count);
    }
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] > vals[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }
    public int getKey(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int maxIndex = countLetters(message);
        int key = maxIndex - alphabet.indexOf('e');
        System.out.println("The encrypted message: " + message);
        System.out.println("The most frequently used letter is " + alphabet.charAt(maxIndex));
        if (key > 0) {
            return key;
        } else {
            return 26 + key;
        }
    }
    public void decrypt(String encrypted, int key) {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, key);
        System.out.println(message);
    }
    public void testDecrypt() {
        String encrypted = "Grpq x qbpq pqofkd tfqe ilqp lc bbbbbbbbbbbbbbbbbp";
        int key = getKey(encrypted);
        decrypt(encrypted, 26 - key);
    } 
    public String halfOfString(String message, int start) {
        String halved = "";
        for (int i = start; i < message.length(); i += 2) {
            halved += message.charAt(i);
        }
        return halved;
    }
    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        String halved = halfOfString(message,0);
        if (halved.equals("Qk gs")) {
            System.out.println(message + " halved correctly: " + halved);
        } else {
            System.out.println(message + " halved incorrectly: " + halved);
        }
        halved = halfOfString(message,1);
        if (halved.equals("bmZi")) {
            System.out.println(message + " halved correctly: " + halved);
        } else {
            System.out.println(message + " halved incorrectly: " + halved);
        }
    }
    public String decryptTwoKeys(String encrypted) {
        String halved1 = halfOfString(encrypted, 0);
        String halved2 = halfOfString(encrypted, 1);
        int key1 = getKey(halved1);
        int key2 = getKey(halved2);
        System.out.println("The two keys found: " + key1 + ", " + key2);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        return message;
    }
    public void testDecryptTwoKeys() {
        String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String message = decryptTwoKeys(encrypted);
        System.out.println("The decrypted message is " + message);
        CaesarCipher cc = new CaesarCipher();
        message = cc.encryptTwoKeys(encrypted, 26 - 14, 26 - 24);
        System.out.println("The decrypted message is " + message);        
        FileResource fr = new FileResource();
        message = decryptTwoKeys(fr.asString());
        System.out.println("The decrypted message is " + message);
    }
}