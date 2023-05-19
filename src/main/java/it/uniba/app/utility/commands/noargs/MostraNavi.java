package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.PrintShips;
import it.uniba.app.utility.commands.noargs.NoArgs;

public class MostraNavi implements NoArgs {
    /**
     *  Il metodo stampa le navi da affondare
     *  durante una classica partita.
     */
    public void execute(){
        PrintShips.showShips();
    }
}
