package it.uniba.app.battleship.controller;

/**
 * Gestisce la richiesta di uscita dall'applicazione.
 * <hr>
 * <b>Control class</b>
 */
public final class ExitController {
    private boolean requestedExit = false;
    private static ExitController instance;

    private ExitController() { };

    public static ExitController getInstance() {
        if (instance == null) {
            instance = new ExitController();
        }
        return instance;
    }

    /**
     * Registra una richiesta di uscita dall'applicazione.
     */
    public void requestExit() {
        requestedExit = true;
    }

    /**
     * Fornisce lo stato della richiesta di uscita dalla applicazione.
     *
     * @return <code>true</code> se è stata effettuata una richiesta, <code>false</code> altrimenti.
     */
    public boolean isExitRequested() {
        return requestedExit;
    }
}
