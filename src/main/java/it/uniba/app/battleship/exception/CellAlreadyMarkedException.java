package it.uniba.app.battleship.exception;

/**
* Eccezione che segnala il fatto che una cella è già stata segnata come bersaglio di un colpo.
*/
public class CellAlreadyMarkedException extends GameException {
    /**
     * Invoca il costruttore della superclasse.
     */
    public CellAlreadyMarkedException() {
        super("La cella è già stata colpita");
    }
}
