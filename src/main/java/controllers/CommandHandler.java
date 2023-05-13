package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CommandHandler {
    private static final String MSG_INVALID = "Comando non valido";

    public static String readCommand() {
        BufferedReader buffer = null;
        String command = "";

        try {
            buffer = new BufferedReader(new InputStreamReader(System.in));
            command = buffer.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return command;
    }

    public static boolean executeCommand(final String input) {
        String[] tokens = input.split(" ");     // contiene il comando e i parametri
        String comand   = tokens[0];                  // contiene il comando

        switch (comand) {
            case "/prova":
                if (needParams(tokens, 2)) {
                    // codice per eseguire comando "/prova"
                    return true;
                }
                break;
            default:
                break;
        }
        System.out.println(MSG_INVALID);
        return false;
    }

    private static boolean needParams(final String[] tokens, final int params) {
        if (tokens.length != params + 1) {  // +1 perchè tokens[0] è il comando
            return false;
        }
        return true;
    }

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
