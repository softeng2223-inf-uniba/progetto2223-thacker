package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.DifficultyManager;
import it.uniba.app.utility.PrintHandler;
import it.uniba.app.utility.commands.noargs.NoArgs;

public class Facile implements NoArgs {
    public void execute(){
        DifficultyManager.setEasyLevel();
        PrintHandler.printLevelName();
    }
}
