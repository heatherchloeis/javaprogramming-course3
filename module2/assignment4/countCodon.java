
/**
 * Write a description of countCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class countCodon {
    private HashMap<String, Integer> codonFreqs;
    
    public countCodon() {
        codonFreqs = new HashMap<String, Integer>();
    }
    public void buildCodonMap(String dna, int start) {
        for (int i = start; i < dna.length() - 3; i+=3) {
            String codon = dna.substring(i, i + 3);
            if (codonFreqs.keySet().contains(codon)) {
                codonFreqs.put(codon, codonFreqs.get(codon) + 1);
            } else {
                codonFreqs.put(codon, 1);
            }
        }
    }
    public void testCodonMap() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int start = 2;
        buildCodonMap(dna, start);
        System.out.println("The number of unique codons equals " + codonFreqs.size());
        for (String s: codonFreqs.keySet()) {
            int freq = codonFreqs.get(s);
            System.out.println("The codon " + s + " occurs " + freq + " times.");
        }
    }
}
