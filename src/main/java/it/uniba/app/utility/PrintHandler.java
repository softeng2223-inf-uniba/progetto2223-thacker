package it.uniba.app.utility;

/**
 * La classe PrintHandler fornisce funzionalità
 * per la gestione delle stampe a video.
 */
public class PrintHandler {
    private static final String PRINTEXAMPLE = "PROVA";

    /**
     * Stampa il messaggio fornito in input sulla console senza andare a capo.
     * @param msg messaggio da stampare
     */
    public static void print(final String msg) {
        System.out.print(msg);
    }
    /**
     * Stampa il messaggio fornito in input sulla console andando a capo.
     * @param msg messaggio da stampare
     */
    public static void println(final String msg) {
        System.out.println(msg);
    }

    /**
     * Stampa il livello di difficoltà corrente.
     */
    public static void printLevelName() {
        println("OK: " + DifficultyManager.getLevelName());
    }
}
