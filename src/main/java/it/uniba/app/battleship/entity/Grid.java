package it.uniba.app.battleship.entity;

import java.util.HashSet;

import java.util.Arrays;

/**
 * La classe {@code Grid} rappresenta la mappa delle navi
 * e tiene traccia delle coordinate già colpite.
 * Ha un solo costruttore che non prende parametri e si dichiara
 * in questo modo:
 * <p><quoteblock><pre>
 * Grid gameGrid = new Grid();
 * </pre><quoteblock></p>
*/
public class Grid implements Cloneable {
    private static final int DEFAULT_SIZE    = 10;
    private static final int LARGE_SIZE      = 18;
    private static final int EXTRALARGE_SIZE = 26;

    private static final String STR_DOT   = "\u00B7";
    private static final String STR_WATER = "~";
    private static final String STR_HIT   = "X";

    private static int chosenSize = DEFAULT_SIZE;
    private Ship[][] map;
    private HashSet<Coordinate> hits;


    /**
     * Costruttore che permette di istanziare
     * un oggetto {@code Grid} di dimensione {@code chosenSize}.
     */
    public Grid() {
        map = new Ship[chosenSize][chosenSize];
        for (int i = 0; i < chosenSize; i++) {
            Arrays.fill(map[i], null);
        }
        hits = new HashSet<Coordinate>();
    }

    /**
     * Imposta la {@code chosenSize} alla dimensione scelta
     * passata come parametro.
     * @param size dimensione scelta.
     * @throws SessionAlreadyStartedException
     */
    public static void setChosenSize(final int size) {
        chosenSize = size;
    }

    /**
     * Restituisce la grandezza della mappa.
     * @return dimensione mappa
     */
    public static int getSize() {
        return chosenSize;
    }

    /**
     * Restituisce la dimensione
     * {@code LARGE_SIZE} che la griglia può assumere.
     */
    public static int getLargeSize() {
        return LARGE_SIZE;
    }

    /**
     * Restituisce la dimensione
     * {@code EXTRALARGE_SIZE} che la griglia può assumere.
     */
    public static int getExtraLargeSize() {
        return EXTRALARGE_SIZE;
    }

    /**
     * Restituisce la dimensione
     * {@code DEFAULT_SIZE} che la griglia può assumere.
     */
    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    /**
     * Restituisce il puntino che viene usato come simbolo
     * per definire una cella che non è ancora stata colpita.
     * @return simbolo cella non colpita
     */
    public static String getDotSymbol() {
        return STR_DOT;
    }

    /**
     * Restituisce il simbolo che viene utilizzato per
     * mostrare l'acqua all'interno della mappa.
     * @return simbolo acqua.
     */
    public static String getWaterSymbol() {
        return STR_WATER;
    }

    /**
     * Restituisce il simbolo che viene utilizzato per mostrare
     * una nave colpita all'interno della mappa.
     * @return simbolo nave colpita.
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
     * Effettua un controllo sulla mappa delle navi nella cella
     * in posizione definita da {@code Coordinate} per controllare
     * se la cella è vuota o se è presente una nave.
     * @param coord coordinate della cella su cui effettuare il controllo
     * @return {@code true} se la cella è vuota, {@code false} se è presente una nave
     */
    public boolean isCellEmpty(final Coordinate coord) {
        return map[coord.getRow()][coord.getCol()] == null;
    }

    /**
     * Effettua un controllo sulle coordinate che sono già state colpite.
     * Se la {@code Coordinate} si rivela essere una coordinata già colpita,
     * allora il controllo darà esito positivo, altrimenti sarà negativo.
     * @param coord coordinate su cui effettuare il controllo
     * @return {@code true} se è una posizione già colpita, {@code false} altrimenti
     */
    public boolean isCellHit(final Coordinate coord) {
        return hits.contains(coord);
    }

    /**
     * Javadoc momentaneo.
     * @param coord
     */
    public void mark(final Coordinate coord) {
        hits.add(coord);
    }

    /**
     * Controlla se la coordinata passata come parametro è all'interno dei
     * confini della mappa.
     * @param coord coordinata su cui effettuare il controllo.
     * @return {@code true} se è all'interno dei confini della mappa, {@code false} altrimenti
     */
    public boolean isWithinBounds(final Coordinate coord) {
        return (coord.getRow() >= 0 && coord.getRow() <= chosenSize - 1)
            && (coord.getCol() >= 0 && coord.getCol() <= chosenSize - 1);
    }

    @Override
    public final Grid clone() {
        Grid clone = null;
        try {
            clone = (Grid) super.clone();
            clone.map = Arrays.copyOf(map, map.length);
            for (int i = 0; i < chosenSize; i++) {
                clone.map[i] = Arrays.copyOf(map[i], map[i].length);
            }
            for (int row = 0; row < chosenSize; row++) {
                for (int col = 0; col < chosenSize; col++) {
                    Coordinate coords = new Coordinate(row, col);

                    if (!this.isCellEmpty(coords)) {
                        Ship ship = this.get(coords).clone();
                        clone.set(coords, ship);
                    }
                }
            }
        } catch (CloneNotSupportedException e) { }
        return clone;
    }

}
