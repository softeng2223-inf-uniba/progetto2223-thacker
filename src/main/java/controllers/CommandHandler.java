package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CommandHandler {
    private static final String MSG_INVALID         = "Comando non valido";
    private static final String MSG_INIT            = "Inserisci comando: ";
	
    public static String readCommand() {
        BufferedReader buffer = null;
        String command = "";
        
        try{
            buffer = new BufferedReader(new InputStreamReader(System.in));
            command = buffer.readLine();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        return command;
    }

}
