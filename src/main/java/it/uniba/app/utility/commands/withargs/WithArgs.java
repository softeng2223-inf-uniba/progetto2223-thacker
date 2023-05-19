package it.uniba.app.utility.commands.withargs;
/**
 * Interfaccia pubblica dei comandi con parametri.
 */
public interface WithArgs {
    /**
     *  Il metodo esegue il codice del comand
     *  con parametri selezionato..
     *
     *  @param args array di stringhe contenente i parametri inseriti
     */
    void execute(String[] args);
}
