
/**
 * Write a description of identifyCharacters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class identifyCharacters {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterFreqs;
    
    public identifyCharacters() {
        characterNames = new ArrayList<String>();
        characterFreqs = new ArrayList<Integer>();
    }
    public void findCharacters() {
        // create file resource
        FileResource fr = new FileResource();
        // for each line in file resource
        for (String s: fr.lines()) {
            s = s.toLowerCase();
            // identify index of first period
            int per = s.indexOf(".");
            if (per != -1) {
                String character = s.substring(0, per);
                if (character.length() < 25) {
                    int idx = characterNames.indexOf(character);
                    if (idx == -1) {
                        characterNames.add(character);
                        characterFreqs.add(1);
                    } else {
                        int value = characterFreqs.get(idx);
                        characterFreqs.set(idx, value + 1);
                    }
                }
            }
        }
    }
    public void testCharacterFreqs() {
        findCharacters();
        System.out.println("The number of characters found is " + characterNames.size());
        for (int i = 0; i < characterNames.size(); i++) {
            System.out.println("The character " + characterNames.get(i) + " has " +
                                characterFreqs.get(i) + " lines.");
        }
    }
}
