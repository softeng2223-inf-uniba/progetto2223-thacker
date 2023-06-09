package it.uniba.app.battleship.exception;

/**
 * Eccezione che segnala il fatto che una sessione di gioco non è ancora iniziata.
*/
public class SessionNotStartedException extends GameException {

    /**
    * Invoca il costruttore della superclasse con un messaggio di errore.
    */
    public SessionNotStartedException() {
        super("La sessione non è ancora iniziata.");
    }
}
