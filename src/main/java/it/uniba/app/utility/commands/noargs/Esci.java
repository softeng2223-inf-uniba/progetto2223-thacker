package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.noargs.NoArgs;

public class Esci implements NoArgs {
    /**
     *  Il metodo permette di uscire in modo
     *  sicuro dal gioco stampando un messaggio 
     *  di conferma.
     */
    public void execute(){
        System.out.println("Esci");
    }
}
