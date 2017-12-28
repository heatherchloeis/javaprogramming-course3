
/**
 * Write a description of wordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class wordLengths {
    public void countWordLengths(FileResource fr, int[] count) {
        for (String word: fr.words()) {
            int len = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if ((i == 0 || i == word.length() - 1) && !(Character.isLetter(ch))) {
                    break;
                }
                len++;
            }
            int lastIndex = count.length - 1;
            if (len > lastIndex) {
                count[lastIndex]++;
            } else {
                count[len]++;
            }
        }
    }
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] count = new int[31];
        countWordLengths(fr, count);
        System.out.println("Note that this file has words that are: ");
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println(count[i] + " words of length " + i);
            }
        }
        System.out.println("The common word length in this file is " + indexOfMax(count));
    }
    public int indexOfMax(int[] values) {
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[max] < values[i]) {
                max = i;
            }
        }
        return max;
    }
}
