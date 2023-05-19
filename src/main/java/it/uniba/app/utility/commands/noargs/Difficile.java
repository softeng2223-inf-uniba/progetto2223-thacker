package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.DifficultyManager;
import it.uniba.app.utility.PrintHandler;
import it.uniba.app.utility.commands.noargs.NoArgs;
/**
 * Classe del comando /difficile.
 * Contiene il codice da eseguire con il comando /difficile.
 */
public class Difficile implements NoArgs {
    /*
     *  Il metodo imposta il livello di difficoltà 
     *  del gioco a difficile e stampa il nome del livello.
     */
    public void execute(){
        DifficultyManager.setHardLevel();
        PrintHandler.printLevelName();
    }
}
