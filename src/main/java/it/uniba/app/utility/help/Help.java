package it.uniba.app.utility.help;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/**
 * <p>
 * Classe principale. Contiene le funzioni che stampa a video il file di testo
 * contenente le istruzioni per l'utilizzo del programma.
 */
public final class Help {
    /**
     * Variabile costante che indica il tempo di attesa tra la stampa di una stringa del titolo e la successiva,
     * in millisecondi.
     */
    private static final int THREAD_DELAY_TITLE = 100;
    /**
     * Variabile costante che indica il tempo di attesa tra la stampa di un carattere e l'altro, in millisecondi.
     *
     */
    private static final int THREAD_DELAY_TEXT = 5;


    private static final File TITLE_FILE = new File("src/main/java/it/uniba/app/utility/help/title.txt");

    /**
     * <p>
     * Costruttore privato di Help.
     * </p>
     * Ha zero argomenti.Dichiarato privato al fine di evitare l'istanziazione della classe
     * (che è una classe di sola utilità e non ha bisogno di essere istanziata).
     */
    private Help() { }
    /**
     * <p>
     * Legge il file title.txt e stampa a video ogni stringa da cui è composto quest'ultimo.
     * </p>
     * Tra la stampa di una stringa  e l'altra c'è un delay di 100 millisecondi.
     *
     */
    private static void printTitle() throws IOException {
        BufferedReader reader = null;
        String title = "";
        try {
        reader = new BufferedReader(new FileReader(TITLE_FILE));
            while (reader.read() != -1) {
                title = reader.readLine();
                Thread.sleep(THREAD_DELAY_TITLE);
                title = title + "\r";
                System.out.println(title);
            }
            title = title + "\r";
        } catch (IOException e) {
            System.out.println("Input/Output error" + e);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException error: " + e);
        }
        reader.close();
    }
    /**
     * <p>
     *
     * Legge il file text.txt e stampa a video ogni carattere da cui è composto quest'ultimo.
     *</p>
     * Tra la stampa di un carattere e l'altro c'è un delay di 5 millisecondi.
     */
    private static void printText() throws IOException {
        BufferedReader readerText = null;
        try {
            readerText = new BufferedReader(new FileReader("src/main/java/it/uniba/app/utility/help/text.txt"));
            int num;
            while ((num = readerText.read()) != -1) {
                System.out.print((char) num);
                Thread.sleep(THREAD_DELAY_TEXT);
            }
        } catch (IOException e) {
                System.out.println("Input/Output error: " + e);
        } catch (InterruptedException p) {
            System.out.println("Thread interrotto" + p);
        }
        readerText.close();
    }
    /**
     * <p>
     * Invoca le uniche due funzioni di Help: printTitle e printText.
     *
     */
    public static void printHelp() {
        try {
            Help.printTitle();
            Help.printText();
        } catch (IOException e) {
            System.out.println("Input/Output error: " + e);
        }
    }
}
