package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Coordinate;
import it.uniba.app.battleship.entity.Grid;

/**
 * Javadoc momentaneo.
 */
public final class StrikeController {
    private StrikeController() { }

    /**
     * Javadoc.
     * @param command
     * @param coord
     * @param grid
     * @return
     */
    public static boolean strike(final String command, final Grid grid) {
            String[] tokens = command.split("");
            char letter = tokens[0].charAt(0);

            int row = Integer.parseInt(tokens[2]);
    }
}
