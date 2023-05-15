package it.uniba.app.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import it.uniba.app.game.DifficultyManager;

import java.io.IOException;

/**
 * Modella un gestore di comandi.
 *
 * Fornisce metodi utili a gestire le richieste presentate dell'utente ed
 * esegure determinate operazioni.
 */
public final class CommandHandler {

    /* === MESSAGGI === */
    private static final String MSG_INVALID = "Comando non valido";

    /* === COMANDI === */
    private static final String CMD_PROVA1 = "/prova1"; // da rimuovere quando non più utile
    private static final String CMD_PROVA2 = "/prova2"; // da rimuovere quando non più utile
    private static final String CMD_DIFF_SHOW = "/mostralivello";

    /* === SIMBOLI === */
    private static final String SYMBOL_INPUT_PROMPT = "> ";

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

        System.out.print(SYMBOL_INPUT_PROMPT);

        try {
            buffer = new BufferedReader(new InputStreamReader(System.in));
            command = buffer.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
        System.out.println(inputMessage);
        return readCommand();
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

            case CMD_DIFF_SHOW:
                if (needParams(tokens, 0)) {
                    System.out.println("Il livello di difficoltà selezionato è: "
                        + DifficultyManager.getLevelName());
                    System.out.println("Il numero massimo di tentativi falliti è: "
                        + DifficultyManager.getMaxFailedAttempts());
                    return true;
                }
                break;

            default:
                break;
        }
        System.out.println(MSG_INVALID);
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

    /**
     * La funzione controlla se il comando presente in tokens
     * contiene un numero di parametri compreso tra min e max.
     *
     * @param tokens comando da eseguire con i parametri
     * @param min    numero minimo di parametri che il comando deve avere
     * @param max    numero massimo di parametri che il comando deve avere
     * @return true se il comando ha un numero di parametri compreso tra min e max,
     *         false altrimenti
     */
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
}
