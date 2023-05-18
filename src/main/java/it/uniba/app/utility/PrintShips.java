package it.uniba.app.utility;
import it.uniba.app.game.Ship;

/**
 * Classe necessaria per eseguire il comando
 * {@code /mostranavi} e ottenere l'output desiderato.
 * Permette di stampare a video, di ogni tipo
 * di nave, il nome, la dimensione in quadrati e il numero
 * di esemplari da affondare. Utilizza i metodi
 * implementati in ciascuna sottoclasse di {@code Ship}
 * relativi alla stampa delle informazioni di ciascuna
 * specializzazione di nave.
*/
public final class PrintShips {
    /**
     * Costruttore privato per impedire
     * l'istanziazione di oggetti di questa classe.
     */
    private PrintShips() { }

    private static String incrementNameWhiteSpace(final String nameSpace, final Ship ship) {
        if (ship.getName() == "Cacciatorpediniere") {
            return nameSpace.concat("  ");
        }
        if (ship.getName() == "Incrociatore") {
            return nameSpace.concat("      ");
        }
        if (ship.getName() == "Corazzata") {
            return nameSpace.concat("   ");
        }
        return nameSpace;
    }
}
