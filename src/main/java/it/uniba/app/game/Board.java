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

     /**
     * JavaDoc momentaneo.
     * @param coord
     * @param ship
     */
    public void setElement(final Coordinate coord, final Ship ship) {
        shipMap[coord.getRow()][coord.getCol()] = ship;
    }

    /**
     * JavaDoc momentaneo.
     * @param coord
     * @return qualcosa
     */
    public Ship getElement(final Coordinate coord) {
        return shipMap[coord.getRow()][coord.getCol()];
    }

    /**
     * JavaDoc momentaneo.
     * @return momentaneo.
     */
    public Ship[][] getshipMap() {
        return shipMap;
    }

    /**
     * JavaDoc momentaneo.
     * @return momentaneo.
     */
    public String[][] getPlayerMap() {
        return playerMap;
    }

    /**
     * JavaDoc momentaneo.
     */
    private void set(final Coordinate coord, final String val) {
        playerMap[coord.getRow()][coord.getCol()] = val;
    }

    /**
     * JavaDoc momentaneo.
     * @param coord momentaneo
     * @return momentaneo
     */
    private String get(final Coordinate coord) {
        return playerMap[coord.getRow()][coord.getCol()];
    }

    /**
     * JavaDoc momentaneo.
     * @param coord momentaneo
     */
    public void updateMap(final Coordinate coord) {
        boolean occupied = isCellOccupiedByShip(coord);
        if (occupied) {
            set(coord, "X");
        } else {
            set(coord, "~");
        }
    }

    /**
     * JavaDoc Momentaneo.
     * @param coord momentaneo
     * @return momentaneo
     */
    public boolean isAlreadyMarked(final Coordinate coord) {
        if (get(coord) == "X" || get(coord) == "~") {
            return false;
        }
        return true;
    }

}
