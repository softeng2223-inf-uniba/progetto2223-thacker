package it.uniba.app.utility.commands;

import com.sun.jdi.request.InvalidRequestStateException;
import it.uniba.app.utility.commands.noargs.*;
import java.util.HashMap;
import java.util.Map;
public class FlagHandler {
    private static final String MSG_FLAG_NOT_RECOGNIZED = "Flag non riconosciuto";
    private static final Map<String, NoArgs> flags = new HashMap<>();
    static{
        flags.put("--help", new Help());
        flags.put("-h", new HelpCommand());
    }
    /**
     *  Esegue il comando corrispondente alla flag inserito.
     *  I comandi flag non hanno argomenti, quindi passare un array di stringhe
     *  con una dimensione maggiore di 1 provocherà la stampa di un errore. 
     *  Se il flag è riconosciuto, viene eseguito il comando corrispondente.
     *  
     *  @param args array di stringhe contenente il flag inserito
     */
    public static void execute(final String[] args){
        if(args.length == 1){
            NoArgs flagInput = flags.get(args[0]);
            if(flagInput != null){
                flagInput.execute();
            }else{
                System.out.println(MSG_FLAG_NOT_RECOGNIZED);
            }
        }else System.out.println(MSG_FLAG_NOT_RECOGNIZED);
    }
}
