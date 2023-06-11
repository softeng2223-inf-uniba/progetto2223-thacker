package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Ship;
import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.utility.Color;

/**
 * Javadoc momentaneo.
 */
public final class StrikeController {
    private static final int CHAR_CONVERT = 97;
    private static final int NUM_START_INDEX = 2;

    private StrikeController() { }

    /**
     * Javadoc momentaneo.
     * @param command
     * @return
     */
    public static Coordinate convert(final String command) {
        char letter = command.charAt(0);
        int col = letter - CHAR_CONVERT;

        String sub = command.substring(NUM_START_INDEX);
        int row = Integer.parseInt(sub) - 1;

        return new Coordinate(row, col);
    }

    /**
     * Javadoc.
     * @param command
     * @param grid
     * @return
     */
    public static boolean strike(final String command, final Grid grid)
        throws CellAlreadyMarkedException, OutOfMapException {
            Coordinate coord = convert(command);
            if (!grid.isWithinBounds(coord)) {
                throw new OutOfMapException();
            }

            if (grid.isCellHit(coord)) {
                throw new CellAlreadyMarkedException();
            }

            grid.mark(coord);
            if (!grid.isCellEmpty(coord)) {
                Ship ship = grid.get(coord);
                ship.hit();
                if (ship.isSunk()) {
                    System.out.println(
                        Color.get("red")
                        + "\nCOLPITO E AFFONDATO\n"
                        + Color.getReset()
                    );
                } else {
                    System.out.println(
                        Color.get("blue")
                        + "\nCOLPITO\n"
                        + Color.getReset()
                    );
                }
                return true;
            }
            return false;
        }
}
