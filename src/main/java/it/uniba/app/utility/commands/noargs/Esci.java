package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.CommandHandler;
import it.uniba.app.utility.PrintHandler;
/**
 * Classe del comando /esci.
 * Contiene il codice da eseguire con il comando /esci.
 */
public class Esci implements NoArgs {
    private static final String MSG_DENIAL = "Operazione annullata";
    private static final String MSG_CONFIRM = "Sicuro di voler proseguire? (si/no)";
    private static final String MSG_COMMAND_NOT_RECOGNIZED = "Comando non riconosciuto, operazione annullata";

    private static final String CMD_CONFIRM_YES = "si";
    private static final String CMD_CONFIRM_NO = "no";

    private static boolean requestedExit = false;

    /**
     *  Il metodo permette di uscire in modo
     *  sicuro dal gioco stampando un messaggio
     *  di conferma.
     */
    public void execute() {
        if (confirm()) {
            requestedExit = true;
        }
    }

    /**
     * Controlla se il comando Esci è stato eseguito.
     *
     * @return <code>true</code> se è stato eseguito il comando di uscita, <code>false</code> altrimenti
     */
    public static boolean exitHasBeenRequested() {
        return requestedExit;
    }

    private static boolean confirm() {
        PrintHandler.println(MSG_CONFIRM);
        String command = CommandHandler.read();
        if (command.equals(CMD_CONFIRM_YES)) {
            return true;
        } else if (command.equals(CMD_CONFIRM_NO)) {
            PrintHandler.println(MSG_DENIAL);
        } else {
            PrintHandler.println(MSG_COMMAND_NOT_RECOGNIZED);
        }
        return false;
    }
}
