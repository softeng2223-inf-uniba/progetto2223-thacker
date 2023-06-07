package it.uniba.app.new_design.battleship.entity;

import java.util.LinkedList;
import java.util.Arrays;

/**
 * Javadoc momentaneo.
 */
public class Grid {
    private static final int SIZE = 10;
    private static final String STR_DOT = "\u00B7";
    private static final String STR_WATER   = "~";
    private static final String STR_HIT     = "X";

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
     * @return
     */
    public static String getWaterSymbol() {
        return STR_WATER;
    }

    /**
     * Javadoc momentaneo.
     * @return
     */
    public static String getHitSymbol() {
        return STR_HIT;
    }

   /**
      * Inserisce nella mappa delle navi, nella posizione individuata 
      * dalla {@code Coordinate} passata come parametro la nave definita dal 
      * tipo {@code ship}.
      * @param coord coordinate della cella in cui inserire la nave.
      * @param ship nave da inserire nella cella.
      */
    public void set(final Coordinate coord, final Ship ship) {
        map[coord.getRow()][coord.getCol()] = ship;
    }

    /**
     * Restituisce l'elemento che si trova nella mappa delle navi,
     * nella posizione individuata dalla {@code Coordinate} passata
     * come parametro.
     * @param coord coordinate della cella in cui si trova l'elemento
     * da restituire.
     * @return elemento nella posizione definita da {@code Coordinate}
     * nella mappa delle navi.
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
