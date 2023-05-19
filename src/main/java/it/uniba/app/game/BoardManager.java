package it.uniba.app.game;

/**
 * (Da essere deprecata) Gestisce la Board della partita.
*/
public final class BoardManager {
    private static final int NUM_ROW = 10;

    private static Board board = new Board();

    private BoardManager() { };

    public static int getDim() {
        return NUM_ROW;
    }

    /**
     * Fornisce l'accesso alla Board di gioco.
     * @return riferimento ad un oggetto Board.
     */
    public static Board access() {
        return board;
    }
}
