package it.uniba.app.new_design.battleship.entity;

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
 * dopo averli già impostati nel costruttore, la classe {@code Coordinate} offre metodi {@code get} e {@code set}
 * <p> <blockquote> <pre>
 * coord.setX(1);
 * coord.setY(7);
 * int xValue = coord.getX();
 * int yValue = coord.getY();
 * </pre> </blockquote> </p>
 */
public class Coordinate implements Cloneable {
    /**
     * col rappresenta il valore della coordinata che si
     * vuole assegnare come colonna.
     */
    private int col;
    /**
     * row rappresenta il valore della coordinate che si
     * vuole assegnare come riga.
     */
    private int row;
    /**
     * Costruisce un oggetto {@code Coordinate} prendendo come parametri
     * due variabili di tipo {@code int} che rappresentano valore dell'indice
     * delle righe e delle colonne.
     * @param r indice della riga
     * @param c indice della colonna
     */
    public Coordinate(final int r, final int c) {
        row = r;
        col = c;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(final int c) {
        col = c;
    }

    public void setRow(final int r) {
        row = r;
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException err) { }
        return obj;
    }

    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Coordinate)) {
            return false;
        }

        return this.row == ((Coordinate) other).row
            && this.col == ((Coordinate) other).col;
    }

    public String toString() {
        return "Coordinate: [" + col + " " + row + "]";
    }
}


