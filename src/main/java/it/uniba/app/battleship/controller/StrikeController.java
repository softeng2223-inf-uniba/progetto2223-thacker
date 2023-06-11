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
    public static boolean strike(final String command,
        final Coordinate coord, final Grid grid) {
            String[] tokens = command.split("");
            char letter = tokens[0].charAt(0);
    }
}
