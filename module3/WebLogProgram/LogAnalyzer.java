
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
     public void readFile(String filename) {
         // complete method
         FileResource resource = new FileResource(filename);
         String ip;
         Date time;
         String req;
         int status;
         int bytes;
         for (String s: resource.lines()) {
             LogEntry le = WebLogParser.parseEntry(s);
             records.add(le);             
         }
     }
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPAddr = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPAddr.contains(ipAddr)) {
                 uniqueIPAddr.add(ipAddr);
             }
         }
         return uniqueIPAddr.size();
     }
     public void uniqueIPOnDay(String date) {
         ArrayList<String> uniqueIPOnDay = new ArrayList<String>();
         for (LogEntry le : records) {
             String time = le.getAccessTime().toString();
             String ipAddr = le.getIpAddress();
             if (time.contains(date) && !uniqueIPOnDay.contains(ipAddr)) {
                 uniqueIPOnDay.add(ipAddr);
             }
         }
         System.out.println("The number of unique IP Addresses that had access on " +
                            date + " is " + uniqueIPOnDay.size());
     }
     public void printAllHigherThan(int num) {
         ArrayList<Integer> higherThan = new ArrayList<Integer>();
         for (LogEntry le : records) {
             int status = le.getStatusCode();
             if (status > num) {
                 System.out.println(le);
             }
         }
         System.out.println("The number of IP Addresses with status codes higher than " +
                            num + " is " + higherThan.size());
     }
     public void printAllInRange(int low, int high) {
         ArrayList<Integer> uniqueIPAddr = new ArrayList<Integer>();
         for (LogEntry le : records) {
             int status = le.getStatusCode();
             if (((status >= low) && (status <= high))) {
                 uniqueIPAddr.add(status);
             }
         }
         System.out.println("The number of IP Addresses with status codes between " + low +
                            " and " + high + " equals " + uniqueIPAddr.size());
         System.out.println(uniqueIPAddr);
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public HashMap<String, Integer> countIpVisits() {
         HashMap<String, Integer> visits = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!visits.containsKey(ip)) {
                 visits.put(ip, 1);
             } else {
                 visits.put(ip, visits.get(ip) + 1);
             }
         }
         return visits;
     }
     public int mostIpVisits(HashMap<String, Integer> visits) {
         int max = 0;
         for (String s : visits.keySet()) {
             int comp = visits.get(s);
             if (comp > max) {
                 max = comp;
             }
         }
         return max;
     }
     public ArrayList<String> ipWithMostVisits(HashMap<String, Integer> visits) {
         ArrayList<String> maxvisits = new ArrayList<String>();
         int max = mostIpVisits(visits);
         for (String s : visits.keySet()) {
             int comp = visits.get(s);
             if (comp == max) {
                 maxvisits.add(s);
             }
         }
         return maxvisits;
     }
     public HashMap<String, ArrayList<String>> ipPerDay() {
         HashMap<String, ArrayList<String>> ipOnDate = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString().substring(4, 10);
             if (!ipOnDate.containsKey(date)) {
                 ArrayList<String> ipAddr = new ArrayList<String>();
                 ipAddr.add(ip);
                 ipOnDate.put(date, ipAddr);
             } else {
                 ArrayList<String> ipAddr = ipOnDate.get(date);
                 ipAddr.add(ip);
                 ipOnDate.put(date, ipAddr);
             }
         }
         return ipOnDate;
     }
     public ArrayList<String> dayWithMostVisits(HashMap<String, ArrayList<String>> visits, String date) {
         ArrayList<String> ipAddr = visits.get(date);
         HashMap<String, Integer> maxvisits = new HashMap<String, Integer>();
         for (String s : ipAddr) {
             if (!maxvisits.containsKey(s)) {
                 maxvisits.put(s, 1);
             } else {
                 maxvisits.put(s, maxvisits.get(s) + 1);
             }
         }
         return ipWithMostVisits(maxvisits);
     }
}