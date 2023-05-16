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
    /**
     * Classe principale. 
     * Al suo interno sono contenuti i due metodi di classe PrintTitle e PrintText, i quali permettono relativamente di stampare il titolo e il testo dai corrispondenti file di testo.
     */
    private Help() { }
    /**
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
     * PROVA.
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
     * prova.
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