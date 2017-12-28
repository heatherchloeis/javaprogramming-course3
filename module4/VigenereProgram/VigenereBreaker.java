import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        // create new StringBuilder
        StringBuilder slice = new StringBuilder();
        // for loop beginning with whichSlice and increasing by totalSlices
        for (int i = whichSlice; i < message.length(); i+= totalSlices) {
            // find char corresponding to indexOf i in message
            char c = message.charAt(i);
            // add char to StringBuilder
            slice.append(c);
        }
        // return StringBuilder as string
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        // for loop using klength to create slices
        for (int i = 0; i < klength; i++) {
            // create messageSlice
            String slice = sliceString(encrypted, i, klength);
            // create new caesarcracker with most common char
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(slice);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String s : fr.lines()) {
            s = s.toLowerCase();
            dictionary.add(s);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W");
        int count = 0;
        for (String s : words) {
            s = s.toLowerCase();
            if (dictionary.contains(s)) {
                count++;
            }
        }
        return count;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String s : languages.keySet()) {
            HashSet<String> words = languages.get(s);
            System.out.println("Analyzing encrypted message with " + s + " language.");
            String decrypted = breakForLanguage(encrypted, words);
            System.out.println(decrypted);
        }
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;        
        int keylength = 1;
        String maxWords = "";
        char mostCommon = mostCommonChar(dictionary);
        System.out.println("The most common character is " + mostCommon);
        for (int klength = 1; klength <=100; klength++) {
            int[] key = tryKeyLength(encrypted, klength, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > max) {
                max = count;
                maxWords = decrypted;
                keylength = klength;
            }
        }        
        System.out.println("The message contains " + max + " read words.");
        System.out.println("The key length for decryption is " + keylength);
        return maxWords;
    }
    
    public void breakVigenere () {
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String[] dictionaries = new String[8];
            dictionaries[0] = "Danish";
            dictionaries[1] = "Dutch";
            dictionaries[2] = "English";
            dictionaries[3] = "French";
            dictionaries[4] = "German";
            dictionaries[5] = "Italian";
            dictionaries[6] = "Portuguese";
            dictionaries[7] = "Spanish";
        for (int i = 0; i < dictionaries.length; i++) {
            String language = dictionaries[i];
            FileResource languageFile = new FileResource("dictionaries/" + language);
            HashSet<String> dictionary = readDictionary(languageFile);
            languages.put(language, dictionary);
        }
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        breakForAllLangs(encrypted, languages);
    }
    
    public char mostCommonChar(HashSet<String> dictionary) {
        HashMap<Character, Integer> charFreqs = new HashMap<Character, Integer>();
        for (String s : dictionary) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (!Character.isLetter(ch)) {
                    break;
                } 
                if (charFreqs.containsKey(ch)) {
                    int freq = charFreqs.get(ch);
                    charFreqs.put(ch, freq + 1);
                } else {
                    charFreqs.put(ch, 1);
                }
            }
        }
        char mostCommon = ' ';
        int maxFreq = 0;
        for (char ch : charFreqs.keySet()) {
            int currFreq = charFreqs.get(ch);
            if (Character.isSpaceChar(mostCommon)) {
                mostCommon = ch;
                maxFreq = currFreq;
            } else {
                if (currFreq > maxFreq) {
                    mostCommon = ch;
                    maxFreq = currFreq;
                }
            }
        }    
        return mostCommon;
    }
    
    public void testSliceString() {
        String input = "abcdefghijklmnop";
        int start = 2;
        int skip = 3;
        String slice1 = sliceString(input, start, skip);
        System.out.println(slice1);
    }
    
    public void testKeyLength() {        
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int klength = "flute".length();
        int[] key = tryKeyLength(encrypted, klength, 'e');
    }
    
    public void testRandom() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int key = 38;
        int[] keySet = tryKeyLength(encrypted, key, 'e');
        VigenereCipher vc = new VigenereCipher(keySet);
        String decrypted = vc.decrypt(encrypted);
        FileResource dictionary = new FileResource();
        HashSet<String> words = readDictionary(dictionary);
        int count = countWords(decrypted, words);
        System.out.println("There are " + count + " valid words using this key set.");
    }
}
