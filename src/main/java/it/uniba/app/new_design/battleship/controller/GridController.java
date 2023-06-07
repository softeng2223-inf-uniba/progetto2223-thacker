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
     * @param grid
     * @return
     */
    public String genShipMap(final Grid grid) {
        String str = "      A    B    C    D    E    F    G    H    I    J\n\n";
        for (int row = 0; row < Grid.getSize(); row++){
            str += row + ":  ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                if (grid.isCellHit(coords)) {
                    // concatena stringa, ci vuole un altro 
                    // controllo per vedere se è acqua o nave
                } else {
                    str += Grid.getDotString();
                }
            }
        }
        return str;
    }
}
