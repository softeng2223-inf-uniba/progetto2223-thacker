package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.noargs.NoArgs;
public class Help implements NoArgs {
    /*
     *  Il comando help stampa un messaggio con una breve
     *  descrizione del gioco e la lista dei comandi disponbili.
     */
    public void execute(){
        System.out.println("help");
    }
}
