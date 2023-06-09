package it.uniba.app.battleship.exception;

/**
 *Eccezione che segnala il fatto che una sessione di gioco è già in corso.
 */
public class SessionAlreadyStartedException extends GameException {

    /**
    * Invoca il costruttore della superclasse con un messaggio di errore.
    */
    public SessionAlreadyStartedException() {
        super("Una sessione di gioco è già in corso");
    }
}
