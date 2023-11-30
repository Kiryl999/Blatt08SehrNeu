package com.example;

public class Main {
    public static void main(String[] args) {
        WordCount test = new WordCount();
        test.worldcounter();
    }
}

/*
 * Wörter < 2 Buchstaben -> Kein Wort
 * Ausgabe in der API, welche Wörter und wie oft jeweils
 * Die IOException darf keinesfalls beim Zählen der Wörter gefangen werden! sonder:
 * Die ausführung des zählens abbrechen und zur main methode geworfen werden
 *   die ausgabe überspringen
 *   im catch block behandelt werden
 * 
 * 
 * Die Prüfung des gegebenen Pfades zu einer Textdatei umfasst folgende Schritte:
    ◦ Gültiger String übergeben?
    ◦ Ist das String-Argument ein Pfad zu einer regulären Datei (nicht
    Verzeichnis oder anderes)?
    ◦ Ist reguläre Datei lesbar?
    Informationen zu einer Datei oder einem Verzeichnis liefert die Klasse File.
 * 
 *  Wörter dürfen nicht manipurlierbar sein und alph. sortiert
 * 
 * Behandlung von Ausnahmen: Wenn ein Wort nicht im Text vorkam: Wie lautet
die korrekte Antwort auf die Frage, wie oft es vorkam?

Wandeln Sie alle Wörter vor Verwendung als key und vor der Prüfung der
Häufigkeit in Kleinbuchstaben um.
 */