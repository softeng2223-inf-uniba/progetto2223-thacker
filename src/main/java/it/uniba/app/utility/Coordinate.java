package it.uniba.app.utility;

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
        row = newRow;
        col = newCol;
    }

    /**
     * Permette di ottenere il valore della coordinata che fa
     * riferimento alla riga.
     * @return indice di colonna della coordianta
     */
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(final int val) {
        row = val;
    }

    public void setCol(final int val) {
        col = val;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException err) {
            System.out.println(err);
        }
        return obj;
    }
}
