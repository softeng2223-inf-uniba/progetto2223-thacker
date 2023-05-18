package it.uniba.app.utility;
import it.uniba.app.game.Ship;
import it.uniba.app.game.Cacciatorpediniere;
import it.uniba.app.game.Incrociatore;
import it.uniba.app.game.Corazzata;
import it.uniba.app.game.Portaerei;
import java.util.LinkedList;

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

    private static String decrementNumberOfExamplesWhiteSpace(String space) {
        int strDim = space.length() - 1;
        String subStr = space.substring(0, strDim);
        space = subStr;
        return space;
    }

    public static void showShips() {

        LinkedList<Ship> list = new LinkedList<Ship>();
        list.add(new Cacciatorpediniere());
        list.add(new Incrociatore());
        list.add(new Corazzata());
        list.add(new Portaerei());

        String numOfInstancesWhiteSpace = "       ";
        String nameWhiteSpace = "";

        for (Ship ship: list) {
            nameWhiteSpace = incrementNameWhiteSpace(nameWhiteSpace, ship);
            String str = ship.getName() + nameWhiteSpace;

            for (int i = 0; i < ship.getShipSize(); i++) {
                str += ship.getShipColoredSymbol() + ship.getShipSymbol()
                    + ship.getShipWhiteSymbol();
            }

            str += numOfInstancesWhiteSpace + "Esemplari:" + ship.getNumberOfInstances();
            decrementNumberOfExamplesWhiteSpace(numOfInstancesWhiteSpace);
            numOfInstancesWhiteSpace = decrementNumberOfExamplesWhiteSpace(numOfInstancesWhiteSpace);
            System.out.println(str);
        }
    }
}
