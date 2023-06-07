package it.uniba.app.new_design.battleship.entity;

import java.util.LinkedList;
import it.uniba.app.utility.Coordinate;

/**
 * Javadoc momentaneo.
 */
public class Grid {
    private static final int SIZE = 10;
    private static final String STR_DOT = "\u00B7";

    private Ship[][] map;
    private LinkedList<Coordinate> hits;

    public Grid() {
        map = new Ship[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(map[i], null);
        }
        hits = new LinkedList<Coordinate>()
    }
}
