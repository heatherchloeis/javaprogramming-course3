
/**
 * Write a description of countWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.*;

public class countWords {
    private HashMap<String, ArrayList<String>> map;
    public countWords() {
        map = new HashMap<String, ArrayList<String>>();
    }
    public void collectWords(File f) {
        String fileName = f.getName();
        FileResource fr = new FileResource(f);
        for (String word: fr.words()) {
            if (map.containsKey(word)) {
                ArrayList<String> list = map.get(word);
                if (!list.contains(fileName)) {
                    list.add(fileName);
                }
            } else {
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(fileName);
                    map.put(word, list);
            }
        }
    }
    private void buildMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            collectWords(f);
        }
    }
    private int findMax() {
        int max = 0;
        for (String word: map.keySet()) {
            int count = map.get(word).size();
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
    private ArrayList<String> wordsInFile(int num) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word: map.keySet()) {
            int freq = map.get(word).size();
            if (freq == num) {
                words.add(word);
            }
        }
        return words;
    }
    private void printFiles(String word) {
        ArrayList<String> fileName = map.get(word);
        for (int i = 0; i < fileName.size(); i++) {
            System.out.println(fileName.get(i));
        }
    }
    public void tester() {
        buildMap();
        int max = findMax();
        System.out.println("Maximum number of files any single word appears in equals " +
                            max);
        System.out.println("All the words that appear in " + max + " files are:");
        ArrayList<String> words = wordsInFile(max);
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            System.out.println(words.size() + " words appear in all 7 files");
        }
        System.out.println("The number of words that appear in " + max + " files is " +
                            words.size());
        System.out.println("To answer the quiz: ");
        words = wordsInFile(4);
        System.out.println("Words in 4 files: " + words.size());
        System.out.println("tree: ");
        printFiles("tree");
    }
}
