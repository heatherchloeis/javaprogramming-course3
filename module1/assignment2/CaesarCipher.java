
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String abcEncrypted1 = abc.substring(key1) + abc.substring(0, key1);
        String abcEncrypted2 = abc.substring(key2) + abc.substring(0, key2);
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            if (Character.isLetter(ch)) {
                char chUpper = Character.toUpperCase(ch);
                int pos = abc.indexOf(chUpper);
                char replacee;
                if (i % 2 == 0) {
                    replacee = abcEncrypted1.charAt(pos);
                } else {
                    replacee = abcEncrypted2.charAt(pos);
                }
                if (chUpper == ch) {
                    encrypted.setCharAt(i, replacee);
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(replacee));
                }
            }
        }
        return encrypted.toString();
    }
    public void testCaesar() {
        int key1 = 21;
        int key2 = 8;
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        message = message.toLowerCase();
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
    }
}
