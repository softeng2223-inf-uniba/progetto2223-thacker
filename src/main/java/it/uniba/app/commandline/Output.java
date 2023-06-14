package it.uniba.app.commandline;

/**
 * {@code <<Boundary>>}<hr>
 * La classe gestisce l'output a video.
 */
public final class Output {
    private static final String ERR_COLOR_CODE = "red";
    private static final String OK_COLOR_CODE = "green";

    private Output() { }

    /**
     * Pulisce lo schermo.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Stampa un messaggio di benvenuto.
     */
    public static void printStartMessage() {
        print("Benvenuto al gioco Battaglia Navale!\n"
                + "Preparati a sfidare e distruggere le navi nemiche.\n"
                + "Fai /help per vedere la lista dei comandi disponibili\n", "yellow");
    }
    /**
     * Stampa un messaggio a video.
     * @param message Il messaggio da stampare.
     */
    public static void print(final String message) {
        System.out.print(message);
    }

    /**
     * Stampa un messaggio a video con un colore specifico.
     * @param message Il messaggio da stampare.
     * @param colorName Il nome del colore da utilizzare.
     */
    public static void print(final String message,
                             final String colorName) {
        print(Color.get(colorName) + message + Color.getReset());
    }

    /**
     * Stampa un messaggio a video con un colore specifico e va a capo.
     * @param message Il messaggio da stampare.
     */
    public static void println(final String message) {
        print(message + "\n");
    }

    /**
     * Stampa un messaggio a video con un colore specifico e va a capo.
     * @param message Il messaggio da stampare.
     * @param colorName Il nome del colore da utilizzare.
     */
    public static void println(final String message,
                               final String colorName) {
        print(message + "\n", colorName);
    }

    /**
     * Stampa il messaggio "Digita un comando" prima di
     * ogni lettura da tastiera.
     */
    public static void printEnterCommand(final boolean isGameStarted) {
        if (isGameStarted) {
            print("partita > Digita un comando: ");
        } else {
            print("menu > Digita un comando: ");
        }
    }

    /**
     * Stampa il messaggio per i comandi:
     * - '/standard'.
     * - '/large'.
     * - '/extralarge'.
     * @param size La dimensione della griglia.
     */
    public static void printSetGridSize(final int size) {
        println("OK, la dimensione della griglia e' ora " + size + "x" + size + ".", OK_COLOR_CODE);
    }

    /**
     * Stampa il messaggio "COLPITO" quando viene colpite una nave.
     */
    public static void printHitShip() {
        println("COLPITO.", "green");
    }

    /**
     * Stampa il messaggio "COLPITO E AFFONDATO" quando viene
     * colpita e affondata una nave.
     */
    public static void printShipSunken() {
        println("COLPITO E AFFONDATO.", "red");
    }

    /**
     * Stampa il messaggio "ACQUA" quando viene colpita l'acqua.
     */
    public static void printHitWater() {
        println("ACQUA.", "blue");
    }

    /**
     * Stampa il messaggio d'errore quando viene passato
     * al main un argomento non riconosciuto.
     * @param arg L'argomento non riconosciuto.
     */
    public static void printFlagNotRecognised(final String[] arg) {
        String str = String.join(" ", arg);
        println("BattleShip: '" + str + "' non e' un comando valido.\n"
                + "Comandi disponibili: --help o -h.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando viene superato
     * il tempo a disposizione per giocare.
     */
    public static void printTimeOut() {
        println("Tempo scaduto, la partita è terminata.", ERR_COLOR_CODE);
    }

    /**
     * Stampa informazioni legate al tempo durante una partita.
     * @param minLimit minuti massimo di gioco (0 = infinito)
     * @param minElapsed minuti di gioco trascorsi
     */
    public static void printSessionTime(final long minLimit, final long minElapsed) {
        String message = "Numero di minuti trascorsi dall'inizio della partita: "
                + minElapsed
                + "\nNumero di minuti ancora disponibili per giocare: ";

            if (minLimit > 0) {
                long remainingMin  = minLimit - minElapsed;
                message += remainingMin;
            } else {
                message += "infiniti";
            }
        print(message);
    }

    /**
     * Stampa l'impostazione del tempo.
     * @param minLimit numero di minuti
     */
    public static void printTimeSetting(final int minLimit) {
        switch (minLimit) {
            case 0:
                print("Hai a disposizione un tempo illimitato");
                break;
            case 1:
                print("Quando inizierai la partita avrai a disposizione "
                + minLimit
                + " minuto");
                break;
            default:
                print("Quando inizierai la partita avrai a disposizione "
                + minLimit
                + " minuti");
                break;
        }

    }

    /**
     * Stampa il messaggio d'errore quando viene inserito
     * da tastiera un comando non riconosciuto.
     * @param command Il comando non riconosciuto.
     */
    public static void printCommandNotRecognised(final String command) {
        println(command + "' non è un comando valido.\n"
                + "Usa il comando '/help' per vedere la lista dei comandi disponibili.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando viene inserito
     * da tastiera un comando con parametro non riconosciuto.
     * @param command Il comando con parametro non riconosciuto.
     */
    public static void printCommandWithParamsNotRecognised(final String command) {
        println(command + "' non e' un comando con parametro valido.\n"
                + "Usa il comando '/help' per vedere la lista dei comandi disponibili.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando viene inserito
     * da tastiera un comando con parametro non numerico.
     * @param command Il comando con parametro non numerico.
     */
    public static void printCommandWithParamsNotNumber(final String command) {
        println("Il parametro fornito per il comando '" + command + "' richiede un valore intero positivo,\n"
        + "ma e' stato passato un valore alfanumerico.\n"
        + "Assicurati di fornire un valore numerico valido per il comando '" + command + "'.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando viene inserito
     * da tastiera un comando con parametro non positivo.
     * @param command Il comando con parametro non positivo.
     */
    public static void printCommandWithParamsNumberNotPositive(final String command) {
        println("Il parametro fornito per il comando '" + command + "' deve essere un numero intero positivo.\n"
        + "Assicurati di fornire un valore numerico valido per il comando '" + command + "'.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando viene inserito
     * da tastiera un comando senza parametro non riconosciuto.
     * @param command Il comando senza parametro non riconosciuto.
     */
    public static void printCommandWithoutParamsNotRecognised(final String command) {
        println(command + "' non e' un comando senza parametro valido.\n"
                + "Usa il comando '/help' per vedere la lista dei comandi disponibili.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando viene invocato
     * un comando con parametro che non è un numero intero >0.
     * @param value Il valore non valido.
     */
    public static void printNumberFormatError(final String value) {
        println(value + "' non è un numero intero >0.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a lanciare un colpo prima dell'inizio della partita.
     */
    public static void printHitSessionNotStarted() {
        println("Non puoi lanciare un colpo se non inizi una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a lanciare un colpo su una cella già colpita.
     * @param coordinate Le coordinate della cella già colpita.
     */
    public static void printHitCellAlreadyHit(final String coordinate) {
        println(coordinate + "' e' una cella gia' colpita.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a lanciare un colpo su una cella fuori dalla griglia.
     * @param coordinate Le coordinate della cella fuori dalla griglia.
     */
    public static void printHitOutOfMap(final String coordinate) {
        println(coordinate + "' e' una cella fuori dalla griglia.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * ad avviare una partita durante una partita già in corso.
     */
    public static void printStartSessionAlreadyStarted() {
        println("Non puoi iniziare una nuova partita se ne hai gia' una in corso.\n"
                + "Utilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a cambiare la dimensione della griglia durante una partita.
     */
    public static void printCantChangeGridSize() {
        println("Non puoi cambiare la dimensione della griglia se hai gia' iniziato una partita.\n"
                + "Utilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a vedere la mappa dei colpi prima dell'inizio della partita.
     */
    public static void printCantShowHitMap() {
        println("Non puoi vedere una mappa dei colpi se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }


    /**
     * Stampa il messaggio d'errore quando si prova
     * a impostare un tempo di gioco durante una partita in corso.
     */
    public static void printCantSetTime() {
        println("Non puoi impostare il tempo di gioco se hai gia' iniziato una partita.\n"
                + "Utilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio quando si invoca il comando '/tempo num'.
     * @param value Il valore del tempo di gioco in minuti.
     */
    public static void printSetTime(final int value) {
        switch (value) {
            case 0 -> println("OK, il tempo di gioco e' ora illimitato.", OK_COLOR_CODE);
            case 1 -> println("OK, il tempo di gioco e' ora " + value + " minuto.", OK_COLOR_CODE);
            default -> println("OK, il tempo di gioco e' ora " + value + " minuti.", OK_COLOR_CODE);
        }
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a impostare una difficolta' durante una partita in corso.
     */
    public static void printCantSetDiffDuringSession() {
        println("Non puoi impostare la difficolta' se hai gia' iniziato una partita.\n"
                + "Utilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a modificare le impostazioni di una difficolta' durante una partita in corso.
     */
    public static void printNotSetCustomDiff() {
        println("Ok ho variato le caratteristiche della difficolta' che hai selezionato,\n"
                + "ma non posso applicarla a questa sessione di gioco perche' e' ancora in corso.\n"
                + "Se vuoi giocare con i tuoi settings devi ricominciare la partita.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio quando si invoca il comando '/nomeDifficoltà'.
     * @param nameDifficulty Il nome della difficoltà.
     * @param maxFailedAttempts Il numero massimo di tentativi fallibili.
     */
    public static void printSetDifficulty(final String nameDifficulty,
                                          final int maxFailedAttempts) {
        println("OK, la difficolta' e' ora " + nameDifficulty + " con " + maxFailedAttempts
                + " tentativi fallibili.", OK_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a digitare una risposta valida per confermare un'operazione.
     */
    public static void printConfirmCommandNotFound() {
        println("Non hai digitato una risposta valida, operazione annullata.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio di conferma quando
     * si prova a confermare un'operazione.
     */
    public static void printNotConfirm() {
        println("OK, operazione annullata.", OK_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * ad abbandonare una partita in corso.
     * @param shipMap La mappa delle navi dell'avversario.
     */
    public static void printEndSessionConfirm(final String shipMap) {
        clearScreen();
        println("\nOK, grazie per aver giocato.\n"
                + "Ecco la mappa delle navi dell'avversario:\n\n\n"
                + shipMap + "\n\n"
                + "Se vuoi giocare di nuovo digita '/gioca'.\n"
                + "Se vuoi uscire dal gioco digita '/esci'.");
    }

    /**
     * Stampa il messaggio di conferma quando si vuole
     * confermare un'operazione.
     * @param operation L'operazione da confermare.
     */
    public static void printConfirmOperation(final String operation) {
        print("Confermi di voler " + operation + "? [si/no]\n"
                + "> ", "yellow");
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a uscire dal gioco senza aver iniziato una partita.
     */
    public static void printCantExit() {
        println("Non puoi uscire dal gioco se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando il gioco
     * non riesce a leggere l'input dell'utente.
     */
    public static void printCantReadInput() {
        println("Impossibile leggere l'input, qualcosa è andato storto.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio d'errore quando il sistema
     * non riesce a clonare un oggetto.
     */
    public static void printCantClone() {
        println("Si e' verificato un errore con la clonazione di un oggetto.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio quando si invoca il comando '/mostralivello'.
     * @param name Nome della difficolta corrente.
     * @param maxFailedAttempts Numero massimo di tentativi fallibili.
     */
    public static void printGameLevel(final String name,
                                      final int maxFailedAttempts) {
        println("Difficolta' corrente: " + name + "\n"
                + "Tentativi fallibili: " + maxFailedAttempts);
    }

    /**
     * Stampa il messaggio quando si invoca il comando /mostratentativi'.
     * @param totalHit Numero totale di tentativi.
     * @param attempts Numero di tentativi falliti.
     * @param maxFailAttempts Numero massimo di tentativi fallibili.
     */
    public static void printShowAttempts(final int totalHit,
                                         final int attempts,
                                         final int maxFailAttempts) {
        println("Tentativi effettuati: " + totalHit + "\n"
                + "tentativi falliti: " + attempts + "\n"
                + "Max-tentativi che puoi fallire: " + maxFailAttempts);
    }

    /**
     * Stampa il messaggio di errore quando si prova
     * a vedere i tentativi senza aver iniziato una partita.
     */
    public static void printShowAttemptsSessionNotStarted() {
        println("Non puoi vedere i tentativi se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }

    /**
     * Stampa il messaggio di errore quando si prova
     * a vedere la griglia senza aver iniziato una partita.
     */
    public static void printShowGridSessionNotStarted() {
        println("Non puoi vedere la griglia se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }

    /**
     * Stampa la mappa dei colpi.
     * @param hitMap La mappa dei colpi.
     */
    public static void printHitMap(final String hitMap) {
        print("Mappa dei colpi:\n\n"
                + hitMap);
    }

    /**
     * Stampa la mappa delle navi.
     * @param shipMap La mappa delle navi.
     */
    public static void printShipMap(final String shipMap) {
        print("Mappa delle navi:\n\n"
                + shipMap);
    }
    /**
     * Stampa il messaggio "partita terminata" dopo che
     * l'utente ha terminato i tentativi a disposizione.
     * @param shipMap La mappa delle navi dell'avversario da stampare.
     */
    public static void printEndGameAttempts(final String shipMap) {
        clearScreen();
        print("Hai esaurito i tentativi, hai perso.\n"
                + "Ecco la mappa delle navi dell'avversario:\n", "red");
        printShipMap(shipMap);
        println("\nSe vuoi giocare di nuovo digita '/gioca'.\n"
                + "Se vuoi uscire dal gioco digita '/esci'.");
    }
    /**
     * Stampa il messaggio "partita vinta" dopo che
     * l'utente ha colpito tutte le navi.
     * @param totalHit
     */
    public static void printWinGame(final int totalHit) {
        clearScreen();
        println("Hai vinto la partita!", "green");
        println("Hai effettuato " + totalHit + " tentativi.", "green");
        println("\nSe vuoi giocare di nuovo digita '/gioca'.\n"
                + "Se vuoi uscire dal gioco digita '/esci'.");
    }

    /**
     * Stampa il messaggio d'errore quando si prova
     * a eseguire un comando di gioco che non dovrebbe
     * essere eseguito durante una partita in corso.
     * @param command Il comando che non può essere eseguito.
     */
    public static void printSessionAlreadyStarted(final String command) {
        println("Non puoi eseguire il comando '" + command + "' durante una partita in corso.\n"
                + "Utilizza il comando '/abbandona' per abbandonare la partita.", ERR_COLOR_CODE);
    }
}
