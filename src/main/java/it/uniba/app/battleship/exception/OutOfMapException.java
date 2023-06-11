package it.uniba.app.battleship.exception;

/**
 * Modella una eccezione di gioco che segnala il tentativo di accesso a una
 * zona della mappa inesistente. Può avvenire quando si tenta di lanciare un colpo
 * che è in una coordinata al di fuori della mappa.
 */
public class OutOfMapException extends GameException {
    /**
     * Solleva l'eccezione che segnala il tentativo di accedere al di fuori
     * della mappa di gioco.
     */
    public OutOfMapException() {
        super("Coordinata non esistente nella mappa di gioco.");
    }
}
