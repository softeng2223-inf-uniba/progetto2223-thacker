package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.utility.Coordinate;
import it.uniba.app.utility.Color;
import it.uniba.app.new_design.battleship.entity.Ship;

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
    public static String genHitsMap(final Grid grid) {
        String str = "      A    B    C    D    E    F    G    H    I    J\n\n";
        for (int row = 0; row < Grid.getSize(); row++) {
            str += row + ":    ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                Ship item = grid.get(coords);
                if (item == null) {
                    str += Grid.getWaterSymbol() + "    ";
                } else {
                    str += item + "    ";
                }
            }
            str += "\n\n";
        }
        return str;
    }

    /**
     * Javadoc momentaneo.
     * @param grid
     * @return
     */
    public String genShipMap(final Grid grid) {
        String str = "      A    B    C    D    E    F    G    H    I    J\n\n";
        for (int row = 0; row < Grid.getSize(); row++){
            str += row + ":   ";
            for (int col = 0; col < Grid.getSize(); col++) {
                Coordinate coords = new Coordinate(row, col);
                if (grid.isCellHit(coords)) {
                    if (grid.isCellEmpty(coords)){
                        str += Color.get("blue") + Grid.getWaterSymbol()
                            + Color.getReset() + "    ";
                    }
                } else {
                    str += Grid.getDotSymbol();
                }
            }
        }
        return str;
    }
}
