package it.uniba.app.game.exceptions;

/**
 * Eccezione che segnala il fatto che una sessione di gioco non è ancora iniziata.
*/
public class SessionNotStartedException extends GameException {

    /**
    * TODO javadoc.
    */
    public SessionNotStartedException() {
        super("La sessione non è ancora iniziata.");
    }
}
