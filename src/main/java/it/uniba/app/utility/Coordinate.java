package it.uniba.app.utility;

import it.uniba.app.game.Board;

/**
 * La classe {@code Coordinate} incapsula il concetto di riga e colonna per la matrice di gioco.
 * Implementa l'interfaccia {@code Cloneable} per poter effettuare modifiche su una copia dell'oggetto
 * senza che queste si riflettano sulle coordinate originali.
 * Ha due costruttori, uno che prende due parametri di tipo {@code int} ed un altro senza parametri.
 * <p><blockquote><pre>
 * Coordinate coord = new Coordinate();
 * Coordinate coord = new Coordinate(5, 8);
 * </pre></blockquote></p>
 * Per il recupero dei valori di riga e colonna all'interno della classe e per poterli modificare
 * dopo averli già impostati nel costruttore, la classe {@code Coordinates} offre metodi {@code get} e {@code set}
 * <p> <blockquote> <pre>
 * coord.setX(1);
 * coord.setY(7);
 * int xValue = coord.getX();
 * int yValue = coord.getY();
 * </pre> </blockquote> </p>
 */
public class Coordinate implements Cloneable {
    private int row;
    private int col;

    /**
     * Costruisce un oggetto {@code Coordinate} con parametri
     * di riga e colonna impostati di default a 0.
     */
    public Coordinate() {
        row = 0;
        col = 0;
    }

    /**
     * Costruisce un oggetto {@code Coordinate} prendendo come parametri
     * due variabili di tipo {@code int} che rappresentano valore dell'indice
     * delle righe e delle colonne.
     * @param newRow indice della riga.
     * @param newCol indice della colonna.
     */
    public Coordinate(final int newRow, final int newCol) {
        row = sanitizeValue(newRow);
        col = sanitizeValue(newCol);
    }

    private int sanitizeValue(final int value) {
        if (value < 0) {
            return 0;
        }
        if (value > Board.getSize() - 1) {
            return Board.getSize() - 1;
        }
        return value;
    }

    /**
     * Permette di ottenere il valore della coordinata che fa
     * riferimento alla riga.
     * @return indice di colonna della coordianta.
     */
    public int getRow() {
        return row;
    }

    /**
     * Permette di ottenere il valore della coordinata che fa
     * riferimento alla colonna.
     * @return indice di colonna della coordianta.
     */
    public int getCol() {
        return col;
    }

    /**
     * Permette di specificare un valore di riga di tipo {@code int} nella coordinata.
     * @param val valore di riga da inserire nella coordinata.
     */
    public void setRow(final int val) {
        row = sanitizeValue(val);
    }

    /**
     * Permette di specificare un valore di colonna di tipo {@code int} nella coordinata.
     * @param val valore di colonna da inserire nella coordinata.
     */
    public void setCol(final int val) {
        col = sanitizeValue(val);
    }

    /**
     * Restituisce un clone dell'oggetto {@code Coordinate} su cui viene invocato
     * il metodo. Bisogna sempre effettuare il cast a {@code Coordinate} quando
     * il metodo {@code clone} viene invocato.
     * <p><blockquote><pre>
     * Coordinate coord = new Coordinate(2, 5);
     * Coordinate cloneCoord = (Coordinate) coord.clone();
     * </pre></blockquote></p>
     * Così facendo eventuali cambiamenti su {@code cloneCoord} non si ripercuoteranno su {@code coord}
     * @return restituisce un clone dell'oggetto Coordinate.
     */
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException err) {
            System.out.println(err);
        }
        return obj;
    }

    /**
     * tostring.
     */
    public String toString() {
        return Integer.toString(row) + " " + Integer.toString(col);
    }
}
