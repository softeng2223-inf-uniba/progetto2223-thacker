package it.uniba.app.commandline;

/**
 * {@code <<Control>>}<hr>
 * Gestisce la richiesta di uscita dall'applicazione.
 */
public final class ExitController {
    private boolean requestedExit = false;

    private static class Holder {
        private static final ExitController INSTANCE = new ExitController();
    }

    private ExitController() { };

    /**
     * Restituisce l'unica istanza della classe (@code ExitController).
     * Se l'oggetto è già stato istanziato, il metodo restituisce
     * la sua istanza, altrimenti ne crea una nuova e la restituisce.
     * @return Oggetto della classe (@code ExitController).
     */
    public static ExitController getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Fornisce lo stato della richiesta di uscita dalla applicazione.
     *
     * @return <code>true</code> se è stata effettuata una richiesta, <code>false</code> altrimenti.
     */
    public boolean isExitRequested() {
        return requestedExit;
    }

    /**
     * Registra una richiesta di uscita dall'applicazione.
     */
    void requestExit() {
        requestedExit = true;
    }
}
