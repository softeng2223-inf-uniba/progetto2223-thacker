package it.uniba.app.new_design.battleship.controller;

/**
 * Gestisce la richiesta di uscita dall'applicazione.
 * <hr>
 * <b>Control class</b>
 */
public final class ExitController {
    private static boolean requestedExit = false;

    private ExitController() { };

    /**
     * Registra una richiesta di uscita dall'applicazione.
     */
    public static void requestExit() {
        requestedExit = true;
    }

    /**
     * Fornisce lo stato della richiesta di uscita dalla applicazione.
     *
     * @return <code>true</code> se è stata effettuata una richiesta, <code>false</code> altrimenti.
     */
    public static boolean isExitRequested() {
        return requestedExit;
    }
}
