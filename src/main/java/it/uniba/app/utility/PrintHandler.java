package it.uniba.app.utility;

/**
 * La classe PrintHandler fornisce funzionalità
 * per la gestione delle stampe a video.
 */
public class PrintHandler {
    private static final String PRINTEXAMPLE = "PROVA";

    /**
     * Stampa il messaggio fornito in input sulla console.
     * @param msg messaggio da stampare
     */
    public void print(final String msg) {
        System.out.println(msg);
    }

    /**
     * Stampa il livello di difficoltà corrente.
     */
    public static void printLevelName() {
        System.out.println("OK: " + DifficultyManager.getLevelName());
    }
}
