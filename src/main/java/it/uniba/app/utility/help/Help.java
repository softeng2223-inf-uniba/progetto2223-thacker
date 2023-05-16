import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * prova.
 */
public final class Help {
    /**
     * Variabile costante che indica il tempo di attesa tra la stampa di una stringa del titolo e la successiva, in millisecondi.
     */
    private static final int THREAD_DELAY_TITLE = 100;
    /**
     * Variabile costante che indica il tempo di attesa tra la stampa di un carattere e l'altro, in millisecondi.
     * 
     */
    private static final int THREAD_DELAY_TEXT = 5;
    /**<p>
     * <b>Costruttore della  classe</b>
     * </p>
     * Ha zero argomenti. 
     * </p>
     *  Dichiarato privato al fine di evitare l'istanziazione della classe 
     * <p> 
     */
    private Help() { }
    /**
     * <p>
     * <b> Classe PrintTitle</b>
     * </p>
     * Legge il file title.txt e stampa a video ogni stringa da cui è composto quest'ultimo. 
     * </p>
     * Tra la stampa di una stringa  e l'altra c'è un delay di 100 millisecondi.
     * 
     */
    private static void printTitle() throws IOException {
        BufferedReader reader = null;
        String title = "";
        try {
        reader = new BufferedReader(new FileReader("c:/Users/stefa/Desktop/HelpStefano/title.txt"));
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
     * <b>Classe printText</b>
     * </p>
     * Legge il file text.txt e stampa a video ogni carattere da cui è composto quest'ultimo.
     * </p>
     * Tra la stampa di un carattere e l'altro c'è un delay di 5 millisecondi.
     */
    private static void printText() throws IOException {
        BufferedReader readerText = null;
        try {
            readerText = new BufferedReader(new FileReader("c:/Users/stefa/Desktop/HelpStefano/text.txt"));
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
     * <b>Classe printHelp</b>
     * </p>
     * Invoca le uniche due funzioni di Help: printTitle e printText.
     * </p>
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