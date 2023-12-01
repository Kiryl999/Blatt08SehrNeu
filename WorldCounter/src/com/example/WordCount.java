package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Object;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class WordCount {

    private static Set<String> words = new HashSet<>(); // unmodifiable set! Vielleicht TreeSet um alph. order
    private static Integer wordCount;

    // public WordCount(Set<String> getWords, int getWordCount) {

    //     public Set<String> words() {
            
    //     }
    // }

    public static Set<String> getWords() { // Vllt TreeSet mal gucken
        return words;
    }

    public int getWordsCount() {
        return wordCount;
    }
    
    public void worldcounter() {
    // String line = null;
    
    // FileInputStream propFile = new FileInputStream( "Grundgesetzt.txt");
    // Properties p = new Properties(System.getProperties());
    // p.load(propFile);
    // System.setProperties(p);
    // System.getProperties().list(System.out);
    
    String userHome = System.getProperty("user.home"); // user/home, also einfach das Homeverzeicnis
    String filePath = userHome + File.separator + "Grundgesetz.txt";
    File file = new File(filePath);
    TreeMap<String, Integer> map = new TreeMap<>(); // Erstmal alle Unique Wörter zählen und anschließend in die TreeMap packen


    try {
        if (!file.exists() | !file.canRead()) {
            throw new FileNotFoundException("Datei nicht gefunden oder nicht lesbar: " + filePath);
        }
        try (BufferedReader f =  new BufferedReader(new FileReader(filePath))) {
            for(String line = f.readLine(); line != null; line = f.readLine()) { // Vielleicht line!= null nicht richtig weil es gibt ja Absätze
                String [] stringLineWords = line.toLowerCase().split("[\\s\\p{Punct}\\p{Cntrl}]+"); // Teilt jedes Wort in der Zeile in einem Array

                for(String wort : stringLineWords) { // Geht jedes Wort in Zeile durch
                    if(wort.length() > 2) {
                        int count = map.containsKey(wort) ? map.get(wort) : 0; 
                        map.put(wort, count + 1); // Fügt Wort in Map ein, wenn vorhanden + 1
                    }   
                }
                // Wenn ein Wort nicht vorkommt soll eine Meldung erscheinen
            }       
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    } catch(FileNotFoundException e) {
        System.out.println(e.getMessage());
        e.printStackTrace(System.err);
    }

    Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);

    for(Map.Entry<String, Integer> entry : unmodifiableMap.entrySet()) {
        if(entry.getValue() != null) {
            System.out.printf("%-20s %10d%n", entry.getKey(), entry.getValue());
        } else {
            System.out.printf("%-20s %10d%n", entry.getKey(), "null");
        }
        
    }

    return;
    }
}