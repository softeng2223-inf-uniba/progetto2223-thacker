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
	
	private static boolean needParams(String[] tokens, int params){
        if(tokens.length != params+1){
            return false;
        }
        return true;
    }    
	private static boolean needParams(String[] tokens, int minNumOfTokens, int maxNumOfTokens){
        params += 1;
        if(minNumOfTokens<maxNumOfTokens){
            int length = tokens.length;
            if(length <= maxNumOfTokens && length >= minNumOfTokens)
                return true;
        }
        return false;
    }

}
