package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.noargs.NoArgs;
import it.uniba.app.game.DifficultyManager;
import it.uniba.app.utility.PrintHandler;

public class MostraLivello implements NoArgs {
    public void execute(){
        PrintHandler.print("Il livello di difficoltà selezionato è: ");
        PrintHandler.println(DifficultyManager.getLevelName());
        PrintHandler.print("Il numero massimo di tentativi fallibili è: ");
        PrintHandler.println(String.valueOf(DifficultyManager.getMaxFailedAttempts()));
    }
}
