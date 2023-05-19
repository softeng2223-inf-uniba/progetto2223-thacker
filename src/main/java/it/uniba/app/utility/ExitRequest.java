package it.uniba.app.utility;

/**
 * Gestisce la richiesta di uscita dall'applicazione.
 */
public final class ExitRequest {
    private static boolean requestedExit = false;

    private ExitRequest() { };

    /**
     * Registra una richiesta di uscita dall'applicazione.
     */
    public static void send() {
        requestedExit = true;
    }

    /**
     * Fornisce lo stato della richiesta.
     *
     * @return <code>true</code> se è stata effettuata una richiesta, <code>false</code> altrimenti.
     */
    public static boolean status() {
        return requestedExit;
    }
}
