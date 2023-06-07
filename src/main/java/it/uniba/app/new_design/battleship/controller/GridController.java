package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.utility.Coordinate;

/**
 * Javadoc momentaneo.
 */
public class GridController {

    private GridController() { }

    /**
     * Javadoc momentaneo.
     * @return
     */
    public String genShipMap() {
        String str = "      A    B    C    D    E    F    G    H    I    J\n\n";
        for (int row = 0; row < Grid.getSize(); row++){
            str += row + ":  ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                // genera stringa.
            }
        }
        return str;
    }
}
