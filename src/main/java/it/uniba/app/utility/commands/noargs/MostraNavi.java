package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.PrintShips;
import it.uniba.app.utility.commands.noargs.NoArgs;

public class MostraNavi implements NoArgs {
    public void execute(){
        PrintShips.showShips();
    }
}
