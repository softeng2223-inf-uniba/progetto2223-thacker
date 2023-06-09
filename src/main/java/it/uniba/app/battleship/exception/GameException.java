package it.uniba.app.battleship.exception;

/**
 * Modella una categoria di eccezioni legate al gioco (battleship).
 */
public class GameException extends Exception {

    /**
     * Costruttore della classe GameException.
     */
    public GameException(final String message) {
        super(message);
    }
}
