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
    }
    private static final Map<String, WithArgs> commandsWithArgs = new HashMap<>();

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
    public static void execute(final String input){
        String[] tokens = splitArgs(input);
        if (tokens.length == 1){
            executeNoParams(tokens[0]);
        } else {
            executeWithParams(tokens);
        }
    }
    public static void executeNoParams(final String command){
        NoArgs commandInput = commandsNoArgs.get(command);
        if(commandInput != null){
            commandInput.execute();
        } else{
            System.out.println(MSG_COMMAND_NOT_RECOGNIZED);
        }
    }
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
