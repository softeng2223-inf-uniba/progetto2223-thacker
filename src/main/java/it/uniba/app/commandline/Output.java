package it.uniba.app.commandline;
import it.uniba.app.utility.Color;

public class Output {
    private static final String ERR_COLOR_CODE = "red";
    private Output() {
    }
    public static void print(final String message) {
        System.out.print(message);
    }
    public static void print(final String message, String colorName) {
        print(Color.get(colorName) + message + Color.getReset());
    }
    public static void println(final String message) {
        print(message + "\n");
    }
    public static void println(final String message, String colorName) {
        print(message + "\n", colorName);
    }
    public static void printSetGridSize(int size) {
        println("OK, la dimensione della griglia e' ora " + size + "x" + size);
    }
    public static void printHitShip() {
        println("COLPITO", "green");
    }
    public static void printShipSunken() {
        println("COLPITO E AFFONDATO", ERR_COLOR_CODE);
    }
    public static void printHitWater() {
        println("ACQUA", "blue");
    }
    public static void printEnterCommand() {
        print("> Digita un comando: ");
    }
    public static void printFlagNotRecognised(String[] arg) {
        String str = String.join(" ", arg);
        println("BattleShip: '" + str + "' non e' un comando valido.\n"
                + "Comandi disponibili: --help o -h.", ERR_COLOR_CODE);
    }
    public static void printTimeOut() {
        println("Tempo scaduto, la partita è terminata.", ERR_COLOR_CODE);
    }
    public static void printCommandNotRecognised(String command) {
        println("[CH] '" + command + "' non è un comando valido.\n"
                + "Usa il comando '/help' per vedere la lista dei comandi disponibili.", ERR_COLOR_CODE);
    }
    public static void printCommandWithParamsNotRecognised(String command) {
        println("[CH] '" + command + "' non e' un comando con parametro valido.\n"
                + "Usa il comando '/help' per vedere la lista dei comandi disponibili.", ERR_COLOR_CODE);
    }
    public static void printCommandWithoutParamsNotRecognised(String command) {
        println("[CH] '" + command + "' non e' un comando senza parametro valido.\n"
                + "Usa il comando '/help' per vedere la lista dei comandi disponibili.", ERR_COLOR_CODE);
    }
    public static void printNumberFormatError(String value) {
        println("[CH] '" + value + "' non è un numero intero >0.", ERR_COLOR_CODE);
    }
    public static void printHitSessionNotStarted() {
        println("[CH] Non puoi lanciare un colpo se non inizi una partita.\n"
                + "\nUtilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }
    public static void printHitCellAlreadyHit(String coordinate) {
        println("[CH] '" + coordinate + "' e' una cella gia' colpita.", ERR_COLOR_CODE);
    }
    public static void printHitOutOfMap(String coordinate) {
        println("[CH] '" + coordinate + "' e' una cella fuori dalla griglia.", ERR_COLOR_CODE);
    }
    public static void printStartSessionAlreadyStarted() {
        println("[CH] Non puoi iniziare una nuova partita se ne hai gia' una in corso.\n"
                + "\nUtilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }
    public static void printCantChangeGridSize() {
        println("[CH] Non puoi cambiare la dimensione della griglia se hai gia' iniziato una partita.\n"
                + "\nUtilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }
    public static void printCantShowHitMap() {
        println("[CH] Non puoi vedere una mappa dei colpi se non hai iniziato una partita.\n"
                + "\nUtilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }
    public static void printCantShowShipMap() {
        println("[CH] Non puoi vedere una mappa delle navi se non hai iniziato una partita.\n"
                + "\nUtilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }
    public static void printCantSetTime() {
        println("[CH] Non puoi impostare il tempo di gioco se hai gia' iniziato una partita.\n"
                + "\nUtilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }
    public static void printSetTime(final int value) {
        if (value == 1) {
            println("OK, il tempo di gioco e' ora " + value + " minuto.");
        } else {
            println("OK, il tempo di gioco e' ora " + value + " minuti.");
        }
    }
    public static void printCantSetDiffDuringSession() {
        println("[CH] Non puoi impostare la difficolta' se hai gia' iniziato una partita.\n"
                + "Utilizza il comando '/abbandona' per abbandonare la partita in corso.", ERR_COLOR_CODE);
    }
    public static void printNotSetCustomDiff() {
        println("[CH] Ok ho variato le caratteristiche della difficolta' che hai selezionato,\n"
                + "ma non posso applicarla a questa sessione di gioco perche' e' ancora in corso.\n"
                + "Se vuoi giocare con i tuoi settings devi ricominciare la partita", ERR_COLOR_CODE);
    }
    public static void printSetDifficulty(String nameDifficulty, int maxFailedAttempts) {
        println("OK, la difficolta' e' ora " + nameDifficulty + " con " + maxFailedAttempts + " tentativi fallibili.");
    }
    public static void printConfirmCommandNotFound() {
        println("[CH] Non hai digitato una risposta valida, operazione annullata.", ERR_COLOR_CODE);
    }
    public static void printNotConfirm() {
        println("OK, operazione annullata.", "green");
    }
    public static void printEndSessionConfirm(String shipMap) {
        println("\nOK, grazie per aver giocato.\n"
                + "Ecco la mappa delle navi dell'avversario:\n\n\n"
                + shipMap + "\n\n"
                + "Se vuoi giocare di nuovo digita '/gioca'.\n"
                + "Se vuoi uscire dal gioco digita '/esci'.");
    }
    public static void printConfirmOperation(String operation) {
        print("Confermi di voler " + operation + "? [si/no]\n"
                + "> ", "yellow");
    }
    public static void printCantExit() {
        println("[CH] Non puoi uscire dal gioco se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }
    public static void printCantReadInput(){
        println("[CH] Impossibile leggere l'input, qualcosa è andato storto.", ERR_COLOR_CODE);
    }
    public static void printCantClone() {
        println("[CH] Si e' verificato un errore con la clonazione di un oggetto", ERR_COLOR_CODE);
    }
    public static void printGameLevel(String name, int MaxFailedAttempts) {
        println("Difficolta' corrente: \n"
                + "Nome: " + name + "\n"
                + "Tentativi fallibili: " + MaxFailedAttempts);
    }
    public static void printShowAttempts(int totalHit, int attempts, int maxFailAttempts) {
        println("Tentativi effettuati: " + totalHit + "/n"
                + "tentativi falliti: " + attempts + "/n"
                + "Max-tentativi che puoi fallire" + maxFailAttempts);
    }
    public static void printShowAttemptsSessionNotStarted() {
        println("[CH] Non puoi vedere i tentativi se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }
    public static void printShowGridSessionNotStarted() {
        println("[CH] Non puoi vedere la griglia se non hai iniziato una partita.\n"
                + "Utilizza il comando '/gioca' per iniziare una partita.", ERR_COLOR_CODE);
    }
    public static void printHitMap(String hitMap) {
        println("\nMappa dei colpi:\n\n"
                + hitMap);
    }
    public static void printShipMap(String shipMap) {
        print("\nMappa delle navi:\n\n"
                + shipMap);
    }
}
