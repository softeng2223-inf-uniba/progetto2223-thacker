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
}
