package it.uniba.app.utility;

import it.uniba.app.game.DifficultyManager;
import it.uniba.app.game.Board;
import it.uniba.app.utility.Color;


/**
 * La classe PrintHandler fornisce funzionalità
 * per la gestione delle stampe a video.
 */
public final class PrintHandler {
    private PrintHandler() { }
    /**
     * Stampa il messaggio fornito in input sulla console senza andare a capo.
     * @param msg messaggio da stampare
     */
    public static void print(final String msg) {
        System.out.print(msg);
    }
    /**
     * Stampa il testo fornito utilizzando il colore specificato.
     * Il testo viene visualizzato nel terminale con il colore corrispondente al nome del colore fornito.
     * Dopo la stampa, viene ripristinato il colore di default nel terminale.
     *
     * @param colorName il nome del colore da utilizzare per stampare il testo
     * @param text il testo da stampare
     */
    public static void print(final String colorName, final String text) {
        System.out.print(get(colorName) + text + getReset());
    }
    /**
     * Stampa il messaggio fornito in input sulla console andando a capo.
     * @param msg messaggio da stampare
     */

    public static void println(final String msg) {
        System.out.println(msg);
    }
    /**
     * Stampa il testo fornito utilizzando il colore specificato, seguito da un carattere di nuova riga.
     * Il testo viene visualizzato nel terminale con il colore corrispondente al nome del colore fornito.
     * Dopo la stampa, viene ripristinato il colore di default nel terminale.
     * Alla fine della riga viene aggiunto un carattere di nuova riga.
     *
     * @param colorName il nome del colore da utilizzare per stampare il testo
     * @param text il testo da stampare
     */
    public static void println(final String colorName, final String text) {
        print(colorName, text);
        System.out.println();
    }
    /**
     * Stampa la mappa dei colpi che usa il giocatore.
     * @param board board contenente le mappe colpi e navi.
     */
    public static void printPlayerMap(final Board board) {
        System.out.print("    A    B    C    D    E    F    G    H    I    L\n\n");
        for (int i = 0; i < Board.getSize(); i++) {
            System.out.print(Integer.toString(i) + ":  ");
            for (int j = 0; j < Board.getSize(); j++) {
                System.out.print(board.getFromPlayerMap(
                    new Coordinate(i, j)) + "    ");
            }
            System.out.print("\n\n");
        }
    }

    /**
     * Stampa il livello di difficoltà corrente.
     */
    public static void printLevelName() {
        println("OK: " + DifficultyManager.getLevelName());
    }
}
