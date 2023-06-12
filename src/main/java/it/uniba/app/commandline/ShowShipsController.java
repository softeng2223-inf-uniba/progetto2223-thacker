package it.uniba.app.commandline;

import it.uniba.app.battleship.entity.Ship;

/**
 * {@code <<Control>>}<hr>
 * Classe necessaria per eseguire il comando
 * {@code /mostranavi} e ottenere l'output desiderato.
 * Permette di stampare a video, di ogni tipo
 * di nave, il nome, la dimensione in quadrati e il numero
 * di esemplari da affondare. Utilizza i metodi
 * implementati in ciascuna sottoclasse di {@code Ship}
 * relativi alla stampa delle informazioni di ciascuna nave.
*/
public final class ShowShipsController {
    private static final String INSTANCE_WHITE_SPACE = "       ";
    private static final String NAME_WHITE_SPACE = "";

    private ShowShipsController() { }

    /**
     * Restiituisce una stringa contenente il nome
     * del tipo di nave specifico con l'aggiunta
     * di uno spazio, che rende possibile la stampa
     * allineata e pulita che viene visualizzata
     * in seguito alla chiamata del comando
     * {@code /mostranavi}.
     *
     * @param nameSpace contiene lo spazio già presente
     * da concatenare con lo spazio necessario a stampare
     * correttamente la nave specifica
     * @param ship contiene il tipo di nave specifico da
     * valutare.
     * @return stringa contenente il nome della nave
     * con l'aggiunta di uno spazio.
     */
    private static String incrementNameWhiteSpace(final String nameSpace, final Ship ship) {
        if (ship.getName().equals("Cacciatorpediniere")) {
            return nameSpace.concat("  ");
        }
        if (ship.getName().equals("Incrociatore")) {
            return nameSpace.concat("      ");
        }
        if (ship.getName().equals("Corazzata")) {
            return nameSpace.concat("   ");
        }
        return nameSpace;
    }
    /**
     * Restituisce lo spazio decrementato necessario
     * per stampare in maniera allineata il numero di
     * {@code esemplari} di ciascuna nave.
     *
     * @param space contiene lo spazio da sottrarre.
     * @return stringa con meno spazio bianco.
     */
    private static String decrementNumberOfInstancesWhiteSpace(final String space) {
        int strDim = space.length() - 1;
        String subStr = space.substring(0, strDim);
        return subStr;
    }
    /**
     * Stampa a video, per ogni tipo di nave,
     * il nome, la dimensione in quadrati
     * e il numero di esemplari da affondare.
     */
    public static String getShipInfo() {
        String numOfInstancesWhiteSpace = INSTANCE_WHITE_SPACE;
        String nameWhiteSpace = NAME_WHITE_SPACE;

        String str = "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < Ship.getNumberOfTypes(); i++) {
            Ship ship = new Ship(i);
            nameWhiteSpace = incrementNameWhiteSpace(nameWhiteSpace, ship);
            b.append(ship.getName());
            b.append(nameWhiteSpace);

            for (int j = 0; j < ship.getSize(); j++) {
                b.append(Color.get(ship.getColor()));
                b.append(ship);
                b.append(Color.getReset());
            }

            b.append(numOfInstancesWhiteSpace);
            b.append("Esemplari:");
            b.append(Ship.getMaxInstances(i));
            numOfInstancesWhiteSpace = decrementNumberOfInstancesWhiteSpace(numOfInstancesWhiteSpace);
            b.append("\n");
        }
        str = b.toString();
        return str;
    }

}
