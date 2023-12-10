package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * WordCount liest und analysiert eine Textdatei, um Wortstatistiken bereitzustellen.
 * Ein TreeMap speichert eindeutige Wörter und ihre Zählungen in sortierter Reihenfolge.
 */
public class WordCount {

    private static Set<String> words = new HashSet<>(); // unmodifiable set!
    private static TreeMap<String, Integer> map = new TreeMap<>(); 
    public static String MaxLange = "";
    private Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
    private String filePath;
    private File file;
    

    WordCount(String dateiname) {

        String userHome = System.getProperty("user.home"); // user/home, also einfach das Homeverzeicnis
        if(userHome == null || userHome.isEmpty()) {
            throw new IllegalArgumentException("Kein Gültiges Verzeichnis gefunden");
        }

        filePath = userHome + File.separator + dateiname;
        if(!filePath.endsWith(".txt")) {
            throw new IllegalArgumentException(filePath + "dieser Pfad führt nicht zu einer ist keine .txt Datei!");
        }

        file = new File(filePath); // Merke: Kein File file, weil wir oben schon File file deklariert haben,
                                                // Man würde hier eine lokale Variable deklarieren
    }
    

    // map.get(key) returns the value of the key

    /**
     * Gibt alle Wörter der .txt Datei aus und die Anzahl deren Vorkommen
     * @param formatString Größe des Feldes für das Wort, gemessen anhand des größten Wortes
     * @param key Geht jedes key durch Map durch
     * @param getWordsCount(key) ruft Methode auf um, die Anzahl der Vorkommen für key zu erhalten
     * @return Gibt alle Wörter und deren Vorkommen aus
     */
    public Set<String> getWords() {

        if (unmodifiableMap == null) {
            throw new NullPointerException("Map darf nicht null sein!");
        }

        if (unmodifiableMap.isEmpty()) {
            System.out.print("Map ist leer! Wort suchen nicht möglich");
        }

        // Methode 1 jetzt in Main statt hier
        // for(String key : unmodifiableMap.keySet()) { // Map muss static sein, damit die Methode map sieht
        //     // System.out.printf(formatString, key , getWordsCount(key));
        //     System.out.println(key.toString());
        // }

        words = unmodifiableMap.keySet();
        return words;
    }

    
    /**
     * Gibt die Anzahl der Vorkommen in Map eines Wortes aus.
     * @param word Welches Wort betrachtet werden soll
     * @throws IllegalArgumentException Wenn Map leer ist, Wort in Map vorhanden oder es kein legitimes Wort ist
     * @throws NullPointerException Wenn die Map null ist
     * @return Die Anzahl der Vorkommen
     */
    public int getWordsCount(String word) { // Muss static sein, damit man die Methode in getWords() aufrufen kann

        if (unmodifiableMap == null) {
            throw new NullPointerException("Map darf nicht null sein!");
        }

        if(word == null) {
            throw new IllegalArgumentException("Kein legitimes Wort!");
        }

        if(unmodifiableMap.isEmpty()) {
            System.out.print("Map ist leer! Wort suchen nicht möglich");
        }

        if (word.length() < 2) {
            System.out.println("Wort muss mindestens 2 Buchstaben enthalten!");
        }

        if (words.isEmpty()) {
            System.out.println("Wort ist leer");
        }

        if(!map.containsKey(word)) {
            System.out.println(0);
        }

        // int wordCount = unmodifiableMap.get(word);
        return unmodifiableMap.get(word);
    }
    
     /**
     * Liest den Inhalt einer bestimmten Textdatei, zählt das Vorkommen jedes Worts
     * und findet das längste Wort. Die Ergebnisse werden im TreeMap gespeichert.
     * 
     * @param userHome Homeverzeichnis jedes Betriebssystem
     * @param filePath Der Pfad zur Datei, wo die Wörter gezählt werden
     * @param file Die File aus der gelesen wird
     * @param line Die Zeile der .txt Datei
     * @param stringLineWords Jedes legitime Wort einer Zeile wird in diesem String Array gespeichert
     * @param wort Das einzelne Wort einer Zeile
     * @param MaxLange Das längste Wort der kompletten .txt Datei
     * @param map Eine TreeMap welches Wort und die Anzahl dessen speichert
     * @throws FileNotFoundException Wenn die angegebene Datei nicht gefunden oder nicht lesbar ist.
     * @throws IllegalArgumentException Wenn der Dateipfad ungültig ist oder die Datei nicht lesbar ist.
     * @throws IOException Falls ein Fehler beim lesen der Datei auftritt
     */
    public void worldcounter() throws IOException { // throws IOException wichtig, damit man andere Exception zuerst thrown kann
    
    BufferedReader f = null;

    try {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException("Datei nicht gefunden oder nicht lesbar: " + filePath);
        }
            f = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8)); 

            for(String line = f.readLine(); line != null; line = f.readLine()) { 
                String [] stringLineWords = line.toLowerCase().split("[\\s\\p{Punct}\\p{Cntrl}]+"); // Teilt jedes Wort in der Zeile in einem Array
                
                for(String wort : stringLineWords) { // Geht jedes Wort in Zeile durch

                    if(wort.length() > MaxLange.length()) { // Finde längstes Wort
                        MaxLange = wort;
                    }

                    if(wort.length() > 1) {
                        // int count = map.containsKey(wort) ? map.get(wort) : 0; 
                        // map.put(wort, count + 1); // Fügt Wort in Map ein, wenn vorhanden + 1

                        // Methode 2 ohne ternerär Operator
                        map.merge(wort, 1,Integer::sum);
                    }   
                }
            }       
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        } finally {
            if (f != null)
                try {
                    f.close();
                } catch (Exception e) {
                    System.err.println("Unerwarteter Fehler.");
                    e.printStackTrace();
                }    
        }
     
    return;
    }
}
