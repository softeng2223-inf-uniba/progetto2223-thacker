package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.DifficultyManager;
import it.uniba.app.utility.PrintHandler;

/**
 * Classe del comando /facile.
 * Contiene il codice da eseguire con il comando /facile.
 */
public class Facile implements NoArgs {
    /**
     *  Il metodo imposta il livello di difficoltà
     *  del gioco a facile e stampa il nome del livello.
     */
    public void execute() {
        DifficultyManager.setEasyLevel();
        PrintHandler.printLevelName();
    }
}
