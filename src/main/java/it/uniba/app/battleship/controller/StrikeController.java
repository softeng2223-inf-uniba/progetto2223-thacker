package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.exception.CellAlreadyMarkedException;

/**
 * Javadoc momentaneo.
 */
public final class StrikeController {
    private StrikeController() { }

    /**
     * Javadoc.
     * @param command
     * @param grid
     * @return
     */
    public static boolean strike(final String command, final Grid grid)
        throws CellAlreadyMarkedException {
            String[] tokens = command.split("");
            char letter = tokens[0].charAt(0);

            int row = Integer.parseInt(tokens[2]);
            Coordinate coord = new Coordinate(row, letter);
            if (!grid.isWithinBounds(coord)) {
                return false;
            }

            if (grid.isCellHit(coord)) {
                throw new CellAlreadyMarkedException();
            }
        }
}
