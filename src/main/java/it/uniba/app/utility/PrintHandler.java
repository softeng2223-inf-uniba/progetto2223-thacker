package it.uniba.app.utility;

/**
 * La classe PrintHandler fornisce funzionalità
 * per la gestione delle stampe a video.
 */
public final class PrintHandler {
    private static final String PRINTEXAMPLE = "PROVA";

    private PrintHandler() { }


    /**
     * Stampa il messaggio fornito in input sulla console.
     * @param msg messaggio da stampare
     */
    public static void print(final String msg) {
        System.out.println(msg);
    }
}
