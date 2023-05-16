package it.uniba.app.game;

import java.util.Arrays;
import it.uniba.app.utility.Coordinate;

/**
 * JavaDoc momentaneo.
 */
public class Board {
    private static final int NUM_OF_CELL = 10;
    private static final String DOT = "\u00B7";
    /**
     * JavaDoc momentaneo.
     */
    private Ship[][] shipMap;
    private String[][] playerMap;

    /**
     * JavaDoc momentaneo.
     */
    public Board() {
        shipMap = new Ship[NUM_OF_CELL][NUM_OF_CELL];
        playerMap = new String[NUM_OF_CELL][NUM_OF_CELL];

        for (int i = 0; i < NUM_OF_CELL; i++) {
            Arrays.fill(shipMap[i], null);
        }

        for (int i = 0; i < NUM_OF_CELL; i++) {
            Arrays.fill(playerMap[i], DOT);
        }
    }

    public static int getSize() {
        return NUM_OF_CELL;
    }

    /**
     * JavaDoc momentaneo.
     */
    public boolean isCellAvailable(final Coordinate coord) {
        return shipMap[coord.getRow()][coord.getCol()] == null;
    }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @return
     */
    public boolean isCellOccupiedByShip(final Coordinate coord) {
        return shipMap[coord.getRow()][coord.getCol()] != null;
    }
}
