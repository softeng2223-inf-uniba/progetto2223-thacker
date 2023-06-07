package it.uniba.app.new_design.battleship.entity;

import java.util.LinkedList;
import java.util.Arrays;
import it.uniba.app.utility.Coordinate;

/**
 * Javadoc momentaneo.
 */
public class Grid {
    private static final int SIZE = 10;
    private static final String STR_DOT = "\u00B7";

    private Ship[][] map;
    private LinkedList<Coordinate> hits;

    /**
     * Javadoc momentaneo.
     */
    public Grid() {
        map = new Ship[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(map[i], null);
        }
        hits = new LinkedList<Coordinate>();
    }

    public static int getSize(){
        return SIZE;
    }

    /**
     * Javadoc momentaneo.
     * @return
     */
    public static String getDotSymbol(){
        return STR_DOT;
    }

    /**
     * Javadoc momentaneo.
     * @param coord
     * @param ship
     */
    public void set(final Coordinate coord, final Ship ship) {
        map[coord.getRow()][coord.getCol()] = ship;
    }

    /**
     * Javadoc momentaneo.
     * @param coord
     * @return
     */
    public Ship get(final Coordinate coord) {
        return map[coord.getRow()][coord.getCol()];
    }

    /**
     * javadoc momentaneo.
     * @param coord
     * @return
     */
    public boolean isCellEmpty(final Coordinate coord) {
        return map[coord.getRow()][coord.getCol()] == null;
    }

    /**
     * Javadoc momentaneo.
     * @param coord
     * @return
     */
    public boolean isCellHit(final Coordinate coord){
        return hits.contains(coord);
    }
}
