package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.new_design.battleship.entity.Grid;

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
            for (int col = 0; col < Grid.getSize(); col++) {
                // genera stringa.
            }
        }
        return str;
    }
}
