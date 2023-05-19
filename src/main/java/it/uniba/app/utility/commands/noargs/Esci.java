package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.noargs.NoArgs;

public class Esci implements NoArgs {
    private static final String MSG_DENIAL = "Operazione annullata";
    private static final String MSG_CONFIRM = "Sicuro di voler proseguire? (si/no)";
    private static final String CMD_CONFIRM_YES = "si";
    private static final String CMD_CONFIRM_NO = "no";
    /**
     *  Il metodo permette di uscire in modo
     *  sicuro dal gioco stampando un messaggio 
     *  di conferma.
     */
    public void execute(){
        System.out.println("Esci");
    }
    private static boolean confirm(final String input){
        System.out.println(MSG_CONFIRM);
        String command = read();
        if(command.equals(CMD_CONFIRM_YES)){
            return true;
        } else if(command.equals(CMD_CONFIRM_NO)){
            System.out.println(MSG_DENIAL);
        } else {
            System.out.println(MSG_COMMAND_NOT_RECOGNIZED);
        }
        return false;
    }
}
