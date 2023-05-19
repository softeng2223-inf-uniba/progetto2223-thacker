package it.uniba.app.utility.commands;

import it.uniba.app.utility.PrintHandler;
import it.uniba.app.utility.commands.withargs.WithArgs;
import it.uniba.app.utility.commands.noargs.NoArgs;
import it.uniba.app.utility.commands.noargs.Difficile;
import it.uniba.app.utility.commands.noargs.Esci;
import it.uniba.app.utility.commands.noargs.Facile;
import it.uniba.app.utility.commands.noargs.Gioca;
import it.uniba.app.utility.commands.noargs.Help;
import it.uniba.app.utility.commands.noargs.Medio;
import it.uniba.app.utility.commands.noargs.MostraLivello;
import it.uniba.app.utility.commands.noargs.MostraNavi;
import it.uniba.app.utility.commands.noargs.SvelaGriglia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *  La classe commandHandler contiene tutto il codice
 *  necessario per gestire i comandi con parametri e senza
 *  parametri inseriti dall'utente.
 */
public final class CommandHandler {
    private static final String ENCODER_USED = "UTF-8";
    private static final String SYMBOL_INPUT_PROMPT = "> ";
    private static final String MSG_COMMAND_NOT_RECOGNIZED = "Comando non riconosciuto";
    private static final Map<String, NoArgs> COMMAND_NO_ARGS = new HashMap<>();
    static {
        COMMAND_NO_ARGS.put("/help", new Help());
        COMMAND_NO_ARGS.put("/esci", new Esci());
        COMMAND_NO_ARGS.put("/gioca", new Gioca());
        COMMAND_NO_ARGS.put("/difficile", new Difficile());
        COMMAND_NO_ARGS.put("/medio", new Medio());
        COMMAND_NO_ARGS.put("/facile", new Facile());
        COMMAND_NO_ARGS.put("/mostranavi", new MostraNavi());
        COMMAND_NO_ARGS.put("/mostralivello", new MostraLivello());
        COMMAND_NO_ARGS.put("/svelagriglia", new SvelaGriglia());
    }
    private static final Map<String, WithArgs> COMMAND_WITH_ARGS = new HashMap<>();
    private CommandHandler() {
    }
    /**
     *  Metodo che legge da tastiera il comando inserito dall'utente.
     *  @return Stringa contenente il comando inserito dall'utente.S
     */
    public static String read() {
        BufferedReader buffer;
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
     *  Metodo che esegue il comando inserito dall'utente.
     *  Nel caso in cui il comando è senza parametri viene richiamata la
     *  funzione che esegue il codice relativo al comando senza parametri.
     *  Altrimenti viene richiamata la funzione che esegue il codice
     *  relativo al comando senza parametri.
     *  @param input Stringa contenente il comando inserito dall'utente.
     */
    public static void execute(final String input) {
        String[] tokens = splitArgs(input);
        if (tokens.length == 1) {
            executeNoParams(tokens[0]);
        } else {
            executeWithParams(tokens);
        }
    }
    /**
     *  Metodo che esegue il comando senza parametri inserito dall'utente.
     *  Se il comando inserito dall'utente è presente nella mappa dei comandi
     *  senza parametri viene richiamata la funzione che esegue il codice
     *  relativo al comando senza parametri. Altrimenti viene stampato un
     *  messaggio di errore.
     *  @param command Stringa contenente il comando inserito dall'utente.
     */
    public static void executeNoParams(final String command) {
        NoArgs commandInput = COMMAND_NO_ARGS.get(command);
        if (commandInput != null) {
            commandInput.execute();
        } else {
            PrintHandler.println(MSG_COMMAND_NOT_RECOGNIZED);
        }
    }
    /**
     *  Metodo che esegue il comando con parametri inserito dall'utente.
     *  Se il comando inserito dall'utente è presente nella mappa dei comandi
     *  con parametri viene richiamata la funzione che esegue il codice
     *  relativo al comando con parametri. Altrimenti viene stampato un
     *  messaggio di errore.
     *  @param args Array di stringhe contenente il comando e i parametri inseriti dall'utente
     */
    public static void executeWithParams(final String[] args) {
        WithArgs commandInput = COMMAND_WITH_ARGS.get(args[0]);
        if (commandInput != null) {
            commandInput.execute(args);
        } else {
            PrintHandler.println(MSG_COMMAND_NOT_RECOGNIZED);
        }
    }
    private static String[] splitArgs(final String input) {
        return input.split(" ");
    }
}
