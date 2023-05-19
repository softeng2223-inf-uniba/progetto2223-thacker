package it.uniba.app.utility.commands;

import it.uniba.app.utility.commands.withargs.*;
import it.uniba.app.utility.commands.noargs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static final String ENCODER_USED = "UTF-8";
    private static final String SYMBOL_INPUT_PROMPT = "> ";
    private static final String MSG_COMMAND_NOT_RECOGNIZED = "Comando non riconosciuto";
    private static final Map<String, NoArgs> commandsNoArgs = new HashMap<>();
    static{
        commandsNoArgs.put("/help", new HelpCommand());
        commandsNoArgs.put("/esci", new Esci());
        commandsNoArgs.put("/gioca", new Gioca());
        commandsNoArgs.put("/difficile", new Difficile());
    }
    private static final Map<String, WithArgs> commandsWithArgs = new HashMap<>();
    /**
     *  Metodo che legge da tastiera il comando inserito dall'utente.
     *  @return Stringa contenente il comando inserito dall'utente.S
     */
    public static String read(){
        BufferedReader buffer;
        String command = "";
        System.out.print(SYMBOL_INPUT_PROMPT);
        try {
            buffer = new BufferedReader(new InputStreamReader(System.in,ENCODER_USED));
            command = buffer.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
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
    public static void execute(final String input){
        String[] tokens = splitArgs(input);
        if (tokens.length == 1){
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
    public static void executeNoParams(final String command){
        NoArgs commandInput = commandsNoArgs.get(command);
        if(commandInput != null){
            commandInput.execute();
        } else{
            System.out.println(MSG_COMMAND_NOT_RECOGNIZED);
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
    public static void executeWithParams(final String[] args){
        WithArgs commandInput = commandsWithArgs.get(args[0]);
        if(commandInput != null){
            commandInput.execute(args);
        } else {
            System.out.println(MSG_COMMAND_NOT_RECOGNIZED);
        }
    }
    private static String[] splitArgs(String input){
        return input.split(" ");
    }
}
