
/**
 * Write a description of vowelReplacement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class vowelReplacement {
    public char toVowel(String phrase, char ch) {
        // create list of vowels
        String vowels = "AEIOUaeiou";
        // compare ch to list of vowels
        int chIndex = vowels.indexOf(ch);
        // if ch is a vowel
        if (chIndex != -1) {
            // return true;
            if (chIndex % 2 == 0) {
                ch = '+';
            } else {
                ch = '*';
            }
        }
        return ch;
    }
    public void testReplaceVowel() {
        String phrase = "Hello Darkness my old friend";
        System.out.println(replaceVowel(phrase));
    }
    public String replaceVowel(String phrase) {
        // transform phrase to encryptable data
        phrase = phrase.toUpperCase();
        // create new string builder with phrase
        StringBuilder encrypted = new StringBuilder(phrase);
        // for loop starting at 0 and ending at phrase.length()
        for (int i = 0; i < encrypted.length(); i++) {
            // look at the ith character (ch) using toVowel() method
            char ch = encrypted.charAt(i);
            // get character returned from method
            char newCH = toVowel(phrase, ch);
            // set as new character in phrase
            encrypted.setCharAt(i, newCH);
        }
        // return encrypted phrase
        return encrypted.toString();
    }
}