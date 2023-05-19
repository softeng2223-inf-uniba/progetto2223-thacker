package it.uniba.app.utility.commands.noargs;

import it.uniba.app.game.BoardManager;
import it.uniba.app.game.Ship;
import it.uniba.app.utility.Coordinate;

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
    public void execute() {
        printGameMap();
    }

        /**
     *  La funzione stampa la mappa di gioco con le nave posizionati.
     *  Nel caso in cui non sia posizionata nave in una determinata coordinata
     *  viene stampato il simbolo "~".
     */
    private static void printGameMap() {
        System.out.print("    A    B    C    D    E    F    G    H    I    L\n\n");
        for (int i = 0; i < BoardManager.getDim(); i++) {
            System.out.print(i + ":  ");
            for (int j = 0; j < BoardManager.getDim(); j++) {
                Ship item = BoardManager.access().getElement(new Coordinate(i, j));
                if (item == null) {
                    System.out.print("~    ");
                } else {
                    System.out.print(item + "    ");
                }
            }
            System.out.print("\n\n");
        }
    }
}
