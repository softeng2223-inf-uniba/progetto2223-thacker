package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.DifficultyManager;
import it.uniba.app.utility.PrintHandler;
/**
 * Classe del comando /mostralivello.
 * Contiene il codice da eseguire con il comando /mostralivello.
 */
public class MostraLivello implements NoArgs {
    /**
     *  Il metodo stampa il livello di difficoltà corrente
     *  e il numero massimo di tentativi fallibili.
     */
    public void execute() {
        PrintHandler.print("Il livello di difficoltà selezionato è: ");
        PrintHandler.println(DifficultyManager.getLevelName());
        PrintHandler.print("Il numero massimo di tentativi fallibili è: ");
        PrintHandler.println(String.valueOf(DifficultyManager.getMaxFailedAttempts()));
    }
}
