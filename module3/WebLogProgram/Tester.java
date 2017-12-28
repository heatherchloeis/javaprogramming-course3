
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        analyzer.printAll();
    }
    public void testUniqueIPAddr() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        int numIP = analyzer.countUniqueIPs();
        System.out.print("There are " + numIP + " unique IP Addresses in this log file.");
    }
    public void testStatusCodes() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData.txt");
        int num = 400;
        analyzer.printAllHigherThan(num);
    }
    public void testUniqueIPOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        String date = "Sep 27";
        analyzer.uniqueIPOnDay(date);
    }
    public void testHighLow() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        int low = 200;
        int high = 299;
        analyzer.printAllInRange(low, high);
    }
    public void testIpVisits() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/shortTestData2.txt");
        HashMap<String, Integer> visits = analyzer.countIpVisits();
        System.out.println(visits);
    }
    public void testMostIpVisits() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        HashMap<String, Integer> visits = analyzer.countIpVisits();
        int max = analyzer.mostIpVisits(visits);
        System.out.println("The most visits by a single IP Address equals " + max);
    }
    public void testIpWithMostVisits() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        HashMap<String, Integer> visits = analyzer.countIpVisits();
        ArrayList<String> maxvisits = analyzer.ipWithMostVisits(visits);
        System.out.println(maxvisits);
    }
    public void testIpPerDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        HashMap<String, ArrayList<String>> map = analyzer.ipPerDay();
        System.out.println(map);
    }
    public void testIpMostVisitsPerDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("data/longTestData2.txt");
        HashMap<String, ArrayList<String>> visits = analyzer.ipPerDay();
        String date = "Sep 29";
        ArrayList<String> maxvisits = analyzer.dayWithMostVisits(visits, date);
        System.out.println(maxvisits);
    }
}
