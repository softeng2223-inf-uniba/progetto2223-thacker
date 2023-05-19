package it.uniba.app.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import it.uniba.app.game.DifficultyManager;

import it.uniba.app.game.InitializeGame;
import it.uniba.app.utility.Coordinate;
import it.uniba.app.game.Board;

import java.io.IOException;
import it.uniba.app.utility.help.Help;
/**
 * Modella un gestore di comandi.
 *
 * Fornisce metodi utili a gestire le richieste presentate dell'utente ed
 * esegure determinate operazioni.
 */
public final class CommandHandler {

    /* === MESSAGGI === */
    private static final String MSG_INVALID_COMMAND = "Comando non valido";
    private static final String MSG_INVALID_ANSWER = "Risposta non valida, comando annullato";
    private static final String MSG_CONFIRM = "Sei sicuro di voler uscire? (si/no)";
    private static final String MSG_DENIAL = "Operazione annullata";
    private static final String FLAG_INVALID = "Parametri di input non validi";

    /* === COMANDI === */
    private static final String CMD_PROVA1 = "/prova1"; // da rimuovere quando non più utile
    private static final String CMD_PROVA2 = "/prova2"; // da rimuovere quando non più utile
    private static final String CMD_DIFF_SHOW = "/mostralivello";
    private static final String CMD_LEFT = "/esci";
    private static final String CMD_CONFIRM = "si";
    private static final String CMD_DENIAL = "no";

    private static final String CMD_HELP = "/help";
    private static final String FLAG_FULL_HELP = "--help";
    private static final String FLAG_SHORT_HELP = "-h";

    private static final String CMD_DIFF_EASY = "/facile";
    private static final String CMD_DIFF_MED  = "/medio";
    private static final String CMD_DIFF_HARD = "/difficile";
    private static final String CMD_START     = "/gioca";
    private static final String CMD_SHOWGRID = "/svelagriglia";

    private static final String CMD_SHOW_SHIPS = "/mostranavi";

    /* === SIMBOLI === */
    private static final String SYMBOL_INPUT_PROMPT = "> ";
    private static final String ENCODER_USED = "UTF-8";
    static Board board = new Board();
    private CommandHandler() {
    }

    /**
     * Richiede all'utente di digitare un comando dal terminale,
     * il quale verrà successivamente convertito in Stringa.
     *
     * @return comando letto da terminale
     */
    public static String readCommand() {
        BufferedReader buffer = null;
        String command = "";

        PrintHandler.print(SYMBOL_INPUT_PROMPT);

        try {
            buffer = new BufferedReader(new InputStreamReader(System.in, ENCODER_USED));
            command = buffer.readLine();
        } catch (IOException e) {
            PrintHandler.println(e.getMessage());
        }

        return command;
    }

    /**
     * Invia un messaggio e poi richiede all'utente di digitare un comando dal
     * terminale,
     * Il quale verrà successivamente convertito in Stringa.
     *
     * @param inputMessage messaggio da inviare prima della richiesta di input
     * @return comando letto in input
     */
    public static String readCommand(final String inputMessage) {
        PrintHandler.println(inputMessage);
        return readCommand();
    }
    /**
     *  Verifica se nel parametro "args" è presente il flag corretto per richiamare l'help.
     *  I flag validi sono FLAG_FULL_HELP e FLAG_SHORT_HELP. E' importante che entrambi i flag
     *  siano posizionati come primi elementi in "args". Se "args" è vuoto non verrà stampato nulla.
     *  Se "args" contiene un solo elemento diverso da FLAG_FULL_HELP e FLAG_SHORT_HELP, verrà
     *  stampato un messaggio di errore relativo ai parametri.
     */
    public static void executeFlags(final String[] args) {
        if (args.length == 1) {
            String command = args[0];
            if (command.equals(FLAG_FULL_HELP) || command.equals(FLAG_SHORT_HELP)) {
                Help.print();
            } else {
                PrintHandler.println(FLAG_INVALID);
            }
        } else {
            PrintHandler.println(FLAG_INVALID);
        }
    }
    /**
     * La funzione esegue il comando passato come parametro.
     * In base al comando esegue un'azione diversa.
     *
     * @param input comando da eseguire
     * @return true se il comando è stato eseguito correttamente, false altrimenti
     */
    public static boolean executeCommand(final String input) {
        String[] tokens = input.split(" "); // contiene il comando e i parametri
        String command = tokens[0]; // contiene il comando

        switch (command) {
            case CMD_PROVA1:
                if (needParams(tokens, 2)) {
                    // codice per eseguire comando "/prova1" con 2 parametri
                    return true;
                }
                break;

            case CMD_PROVA2:
                if (needParams(tokens, 0)) {
                    // codice per eseguire comando "/prova2" con 0 parametri
                    return true;
                }
                break;

            case CMD_HELP:
                if (needParams(tokens, 0)) {
                    Help.print();
                    return true;
                }
                break;

            case CMD_START:
                if (needParams(tokens, 0)) {
                    InitializeGame init = new InitializeGame();
                    init.initGame(board);
                    return true;
                }
                break;

            case CMD_DIFF_SHOW:
                if (needParams(tokens, 0)) {
                    PrintHandler.print("Il livello di difficoltà selezionato è: ");
                    PrintHandler.println(DifficultyManager.getLevelName());
                    PrintHandler.print("Il numero massimo di tentativi fallibili è: ");
                    PrintHandler.println(String.valueOf(DifficultyManager.getMaxFailedAttempts()));
                    return true;
                }
                break;

            case CMD_DIFF_EASY:
                if (needParams(tokens, 0)) {
                    DifficultyManager.setEasyLevel();
                    PrintHandler.printLevelName();
                    return true;
                }
                break;

            case CMD_DIFF_MED:
                if (needParams(tokens, 0)) {
                    DifficultyManager.setMedLevel();
                    PrintHandler.printLevelName();
                    return true;
                }
                break;

            case CMD_DIFF_HARD:
                if (needParams(tokens, 0)) {
                    DifficultyManager.setHardLevel();
                    PrintHandler.printLevelName();
                    return true;
                }
                break;

            case CMD_LEFT:
                if (needParams(tokens, 0)) {
                    if (readConfirm()) {
                        System.exit(0);
                    }
                    return true;
                }
                break;

            case CMD_SHOW_SHIPS:
                if (needParams(tokens, 0)) {
                    PrintShips.showShips();
                    return true;
                }
                break;

            default:
                break;
        }
        PrintHandler.println(MSG_INVALID_COMMAND);
        return false;
    }
    /**
     * La funzione richiede all'utente di confermare l'operazione
     * che sta per essere eseguita.
     * @return true se l'utente conferma, false altrimenti
     */
    private static boolean readConfirm() {
        PrintHandler.println(MSG_CONFIRM);
        String confirm = readCommand();
        if (confirm.equalsIgnoreCase(CMD_CONFIRM)) {
            return true;
        } else if (confirm.equalsIgnoreCase(CMD_DENIAL)) {
            PrintHandler.println(MSG_DENIAL);
        } else {
            PrintHandler.println(MSG_INVALID_ANSWER);
        }
        return false;
    }
    /**
     * La funzione controlla se il comando presente in tokens
     * contiene params parametri.
     *
     * @param tokens comando da eseguire con i parametri
     * @param params numero di parametri che il comando deve avere
     * @return true se il comando ha params parametri, false altrimenti
     */
    private static boolean needParams(final String[] tokens, final int params) {
        if (tokens.length != params + 1) { // +1 perchè tokens[0] è il comando
            return false;
        }
        return true;
    }
    /*
        PREDISPOSIZIONE NEL CASO IN CUI CI SIA BISOGNO
    /**
     * La funzione controlla se il comando presente in tokens
     * contiene un numero di parametri compreso tra min e max.
     *
     * @param tokens comando da eseguire con i parametri
     * @param min numero minimo di parametri che il comando deve avere
     * @param max numero massimo di parametri che il comando deve avere
     * @return true se il comando ha un numero di parametri compreso tra min e max, false altrimenti
     *
    private static boolean needParams(final String[] tokens, final int min, final int max) {
        int minNumOfTokens = min + 1; // +1 perchè tokens[0] è il comando
        int maxNumOfTokens = max + 1; // +1 perchè tokens[0] è il comando
        if (minNumOfTokens < maxNumOfTokens) {
            int length = tokens.length;
            if (length <= maxNumOfTokens && length >= minNumOfTokens) {
                return true;
            }
        }
        return false;
    }
    */
}
