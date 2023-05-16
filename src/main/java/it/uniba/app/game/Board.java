package it.uniba.app.game;

import java.util.Arrays;

/**
 * JavaDoc momentaneo.
 */
public class Board {
    private static final int NUM_OF_CELL = 10;
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
        for (int i = 0; i < NUM_OF_CELL; i++) {
            Arrays.fill(shipMap[i], null);
        }
    }
}
