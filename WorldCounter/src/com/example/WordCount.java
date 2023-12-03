package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.Object;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class WordCount {

    private static Set<String> words = new HashSet<>(); // unmodifiable set! Vielleicht TreeSet um alph. order
    private static Integer wordCount;
    TreeMap<String, Integer> map = new TreeMap<>(); // Erstmal alle Unique Wörter zählen und anschließend in die TreeMap packen
   

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
        
    String userHome = System.getProperty("user.home"); // user/home, also einfach das Homeverzeicnis
    if(userHome == null | userHome.isEmpty()) {
        throw new IllegalArgumentException("Kein Gültiges Verzeichnis gefunden");
    }

    String filePath = userHome + File.separator + "Grundgesetz.txt";
    if(!filePath.endsWith(".txt")) {
        throw new IllegalArgumentException("Pfad führt nicht zu einer .txt Datei!");
    }

    File file = new File(filePath);
    String MaxLange = "";
   
    
    try {
        if (!file.exists() | !file.canRead() | !file.isFile()) {
            throw new FileNotFoundException("Datei nicht gefunden oder nicht lesbar: " + filePath);
        }
         try (BufferedReader f = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            for(String line = f.readLine(); line != null; line = f.readLine()) { 
                String [] stringLineWords = line.toLowerCase().split("[\\s\\p{Punct}\\p{Cntrl}]+"); // Teilt jedes Wort in der Zeile in einem Array
                
                for(String wort : stringLineWords) { // Geht jedes Wort in Zeile durch

                    if(wort.length() > MaxLange.length()) { // Finde längstes Wort
                        MaxLange = wort;
                    }

                    if(wort.length() > 1) {
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




        String formatString = "| %-" + MaxLange.length() + "s | %6s |%n";  // Größe des Feldes für das Wort, gemessen anhand des größten wortes
        System.out.printf(formatString, "Wort", "Anzahl");
        for (Map.Entry<String, Integer> entry : unmodifiableMap.entrySet()) {
            System.out.printf(formatString, entry.getKey(), entry.getValue());
        }
        
    

    return;
    }
}