package it.uniba.app.utility.commands.withargs;

public interface WithArgs {
    /**
     *  Il metodo esegue il codice del comand
     *  con parametri selezionato..
     * 
     *  @param args array di stringhe contenente i parametri inseriti
     */
    void execute(String[] args);
}
