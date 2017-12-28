/**
 * Write a description of wordAnalysis here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class wordAnalysis {
    private ArrayList<String> uniqueWords;
    private ArrayList<Integer> wordFreqs;
    
    public wordAnalysis() {
        uniqueWords = new ArrayList<String>();
        wordFreqs = new ArrayList<Integer>();
    }
    public void findUniqueWords() {
        FileResource fr = new FileResource();
        for (String s: fr.words()) {
            s = s.toLowerCase();
            int idx = uniqueWords.indexOf(s);
            if (idx == -1) {
                uniqueWords.add(s);
                wordFreqs.add(1);
            }
            else {
                int val = wordFreqs.get(idx);
                wordFreqs.set(idx, val + 1);
            }
        }
    }
    public void findWordMaxFreq() {
        findUniqueWords();
        int maxFreq = 0;
        for (int i = 0; i < uniqueWords.size(); i++) {            
            int currFreq = wordFreqs.get(i);
            if (currFreq > maxFreq && currFreq != -1) {
                maxFreq = currFreq;
            }
        }
        int idx = wordFreqs.indexOf(maxFreq);
        String word = uniqueWords.get(idx);
        System.out.println("The most frequently occuring word is " + word + " at " + maxFreq + " times.");
    }
    public void testUniqueWord() {
        findUniqueWords();
        System.out.println("The number of unique words found equals " + uniqueWords.size());
        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println(uniqueWords.get(i) + " occurs " + wordFreqs.get(i) + " times.");
        }
    }
}
