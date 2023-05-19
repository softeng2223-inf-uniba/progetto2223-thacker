package it.uniba.app.utility.commands.noargs;
import it.uniba.app.utility.commands.noargs.NoArgs;
/**
 * Classe del comando /svelagriglia.
 * Contiene il codice da eseguire con il comando /svelagriglia.
 */
public class SvelaGriglia implements NoArgs {
    /**
     *  Il metodo stampa la griglia di gioco
     *  con tutte le navi posizionate.
     *  Nel caso in cui una nave risulta già
     *  colpita verrà mostrato lo stato.
     */
    public void execute(){
        System.out.println("svelagrliglia");
    }
}
