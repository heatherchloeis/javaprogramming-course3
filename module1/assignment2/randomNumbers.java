
/**
 * Write a description of randomNumbers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.util.Random;

public class randomNumbers {
    public void simpleDiceSimulation(int rolls) {
        Random rand = new Random();
        int[] count = new int [13];
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            System.out.println("roll is " + d1 + " and " + d2 + " which equals " + (d1 + d2));
            count[d1 + d2] +=1;
        }
        for (int i = 2; i <= 12; i++) {
            System.out.println(i + " =\t" + count[i] + "\t" + (100.00 * count[i] / rolls));
        }
    }
}
