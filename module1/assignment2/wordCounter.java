
/**
 * Write a description of wordCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.Random;

public class wordCounter {
    public String[] getCommonWords() {
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int idx = 0;
        for (String s: resource.words()) {
            common[idx] = s;
            idx += 1;
        }
        return common;
    }
    public void countWords(FileResource resource, String[] common, int[] count) {
        for (String word: resource.words()) {
            word = word.toLowerCase();
            int idx = indexOf(common, word);
            if (idx != -1) {
                count[idx] += 1;
            }
        }
    }
    public int indexOf(String[] list, String word) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }
    public void countWords() {
        String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] common = getCommonWords();
        int[] count = new int[common.length];
        for (int i = 0; i < plays.length; i++) {
            FileResource resource = new FileResource("data/" + plays[i]);
            countWords(resource, common, count);
            System.out.println(plays[i] + " finished.");
        }
        for (int i = 0; i < common.length; i ++) {
            System.out.println(common[i] + "\t" + count[i]);
        }
    }
}
