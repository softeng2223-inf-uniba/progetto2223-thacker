package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.DifficultyManager;
import it.uniba.app.utility.PrintHandler;
/**
 * Classe del comando /medio.
 * Contiene il codice da eseguire con il comando /medio.
 */
public class Medio implements NoArgs {
    /**
     *  Il metodo imposta il livello di difficoltà
     *  del gioco a medio e stampa il nome del livello.
     */
    public void execute() {
        DifficultyManager.setMedLevel();
        PrintHandler.printLevelName();
    }
}
