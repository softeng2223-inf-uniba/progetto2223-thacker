package it.uniba.app.battleship.exception;

/**
 * Modella un eccezione di gioco dovuta alla non impostazione della Difficoltà.<br>
 * Può essere sollevata, ad esempio, quando prima di iniziare una sessione
 * non è stata impostata una difficoltà di gioco.
 */
public class DifficultyNotSetException extends GameException {

    /** Solleva l'eccezione che segnala la mancata impostazione della Difficoltà. */
    public DifficultyNotSetException() {
        super("Difficoltà non impostata");
    }
}
