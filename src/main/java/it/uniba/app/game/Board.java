package it.uniba.app.game;

import java.util.Arrays;
import it.uniba.app.utility.Coordinate;

/**
 * Rappresenta la tavola da gioco nella sua interezza, comprende la mappa dei {@code colpi}
 * e la mappa {@code navi}.
 * Presenta un solo costruttore con zero argomenti che inizializza entrambe le mappe.
 * <p><quoteblock><pre>
 * Board tavola = new Board();
 * </pre></p>
 * La classe {@code Board} espone metodi per accedere ai campi di entrambe le mappe
 * e per effettuare controlli, come per esempio {@code isCellOccupiedByShip} che prende
 * in input un oggetto di tipo {@link Coordinates}.
 * <p><quoteblock><pre>
 * if (!isCellOccupiedByShip(myCoord)) {
 *      tavola.setElement(myCoord, myShip);
 * }
 * </pre></p>
 */
public class Board {
    private static final int NUM_OF_CELL = 10;
    private static final String DOT = "\u00B7";

    private Ship[][] shipMap;
    private String[][] playerMap;

    /**
     * Il costruttore {@code Board} inizializza sia la mappa dei colpi
     * che la mappa delle navi con elementi di default.
     * Essenzialmente, predispone entrambe le mappe per il posizionamento
     * delle navi e il lancio dei colpi.
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

    /**
     * Restituisce un valore {@code int} che rappresenta la dimensione delle mappe.
     * @return dimensione mappe.
     */
    public static int getSize() {
        return NUM_OF_CELL;
    }

    /**
     * Controlla se, nelle coordinate passate come parametro del metodo, non esiste già una nave
     * nella cella indicata dalle coordinate in {@code shipMap}. Se la cella della mappa non contiene
     * una nave, restituisce {@code true}, altrimenti {@code false}.
     * @param coord coordinate su cui effettuare il controllo sulla mappa.
     * @return {@code true} se la cella definita dalle coordinate è vuota, {@code false} altrimenti.
     */
    public boolean isCellAvailable(final Coordinate coord) {
        return shipMap[coord.getRow()][coord.getCol()] == null;
    }

    /**
     * Controlla se, nelle coordinate passate come parametro del metodo, esiste una nave
     * nella cella indicata dalle coordinate in {@code shipMap}. Se la cella della mappa contiene
     * una nave, restituisce {@code true}, altrimenti {@code false}.
     * @param coord coordinate su cui effettuare il controllo sulla mappa.
     * @return {@code true} se la cella definita dalle coordinate è occupata da una nave, {@code false} altrimenti.
     */
    public boolean isCellOccupiedByShip(final Coordinate coord) {
        return shipMap[coord.getRow()][coord.getCol()] != null;
    }

    /**
     * Inserisce nella cella di {@code shipMap} individuata dalle coordinate passate come parametro
     * la nave definita dal tipo {@code ship}, che è parametro del metodo..
     * @param coord coordinate della cella in cui inserire la nave.
     * @param ship nave da inserire nella cella.
     */
    public void setElement(final Coordinate coord, final Ship ship) {
        shipMap[coord.getRow()][coord.getCol()] = ship;
    }

    /**
     * Restituisce l'elemento che trova nella mappa {@code shipMap} nella cella definita dalle coordinate
     * che vengono passate come argomento.
     * @param coord coordinate della cella da cui estrarre l'elemento.
     * @return elemento della cella.
     */
    public Ship getElement(final Coordinate coord) {
        return shipMap[coord.getRow()][coord.getCol()];
    }


    /**
     * Restituisce la mappa delle navi contenuta all'interno di {@code Board}.
     * @return mappa delle navi.
     */
    public Ship[][] getshipMap() {
        return shipMap;
    }

    /**
     * Restituisce la mappa dei colpi contenuta all'interno di {@code Board}.
     * @return mappa dei colpi.
     */
    public String[][] getPlayerMap() {
        return playerMap;
    }

    /**
     * Inserisce nella mappa dei colpi {@code playerMap} il valore {@code val} nella cella
     * definita dalle coordinate passate come parametro.
     * @param coord coordinate della cella in cui inserire il valore.
     * @param val valore da inserire nella cella.
     */
    private void set(final Coordinate coord, final String val) {
        playerMap[coord.getRow()][coord.getCol()] = val;
    }

    /**
     * Restituisce il valore che trova nella mappa {@code playerMap} nella cella definita dalle coordinate.
     * @param coord coordinate della cella in cui inserire il valore.
     * @return valore della cella individuata dalle coordinate.
     */
    private String get(final Coordinate coord) {
        return playerMap[coord.getRow()][coord.getCol()];
    }

    /**
     * Ad ogni colpo sulla mappa dei colpi, la funzione aggiorna la cella di {@code playerMap}
     * definita dalla coordinata passata come parametro. Se la cella è occupata da una nave,
     * viene inserito il valore {@code X}, altrimenti {@code ~}.
     * @param coord coordinate della cella da aggiornare.
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
     * Controlla se la cella definita dalle coordinate passate come parametro è già stata colpita.
     * @param coord  coordinate della cella da controllare.
     * @return {@code true} se la cella è già stata colpita, {@code false} altrimenti.
     */
    public boolean isAlreadyMarked(final Coordinate coord) {
        if (get(coord) == "X" || get(coord) == "~") {
            return false;
        }
        return true;
    }
}
