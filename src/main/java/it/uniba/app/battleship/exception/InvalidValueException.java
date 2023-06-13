package it.uniba.app.battleship.exception;

/**
 * Modella un eccezione di gioco dovuta all'invio di un valore non consentito.
 */
public class InvalidValueException extends GameException {

    /**
     * Istanzia un eccezione di tipo InvalidValue.
     */
    public InvalidValueException() {
        super("valore non valido");
    }
}
