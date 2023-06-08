package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.new_design.battleship.entity.Ship;

public final class showShips {

    private showShips() { }

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
    public static void show() {
        String numOfInstancesWhiteSpace = "       ";
        String nameWhiteSpace = "";

        String str = "";
        for (int i = 0; i < Ship.getNumberOfTypes(); i++) {
            StringBuilder b = new StringBuilder();
            Ship ship = new Ship(i);
            nameWhiteSpace = incrementNameWhiteSpace(nameWhiteSpace, ship);
            b.append(ship.getName() + nameWhiteSpace);

            for (int j = 0; j < ship.getSize(); j++) {
                b.append(ship);
            }

            b.append(numOfInstancesWhiteSpace + "Esemplari:" + Ship.getMaxInstances(i));
            numOfInstancesWhiteSpace = decrementNumberOfInstancesWhiteSpace(numOfInstancesWhiteSpace);
            str = b.toString();
            System.out.println(str);
        }
    }
}
