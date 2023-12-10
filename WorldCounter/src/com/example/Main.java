package com.example;

/**
 * Hauptklasse für das WordCount-Programm. Erstellt eine Instanz von WordCount,
 * führt Wortzählung und -analyse durch und gibt Ergebnisse aus.
 */
public class Main {
    /**
     * Die Hauptmethode, die das WordCount Programm startet
     * @param args werden hier nicht verwendet
     * @throws IllegalArgumentException Wenn ein Fehler auftrettet
     * @throws Exception Falls ein unbekannter Fehler auftretet
     */
    public static void main(String[] args) {

        try {
            WordCount test = new WordCount("Grundgesetz.txt");
            test.worldcounter();
            String formatString = "| %-" + WordCount.MaxLange.length() + "s | %6s |%n";  
            System.out.printf(formatString, "Wort", "Anzahl"); // Header

            
            for (String wort : test.getWords()) {
                int count = test.getWordsCount(wort);
                System.out.printf(formatString, wort, count);
            }

            
            
            // Methode 2 mit entrySet (Hier für keine anderen Methoden notwendig)
            // for (Map.Entry<String, Integer> entry : unmodifiableMap.entrySet()) {
            //     System.out.printf(formatString, entry.getKey(), entry.getValue());
            // }

        } catch(IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Unbekannter Fehler!");
            e.printStackTrace();
        }
        
    // MacOS Homeverzeichnis kommen -> Finder und dann Shift + Cmd + H    
    }
}

