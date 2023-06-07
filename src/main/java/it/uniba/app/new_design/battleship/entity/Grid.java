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
    public static String getDotString(){
        return STR_DOT;
    }
}
