package it.uniba.app.game.exceptions;

/**
 * TODO javadoc.
 */
public class SessionAlreadyStartedException extends GameException {

    /**
    * TODO javadoc.
    */
    public SessionAlreadyStartedException() {
        super("Una sessione di gioco è già in corso");
    }
}
