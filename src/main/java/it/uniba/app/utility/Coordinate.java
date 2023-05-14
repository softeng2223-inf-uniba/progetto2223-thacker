package it.uniba.app.utility;

/**
 * JavaDoc momentaneo. La classe {@code Coordinate} astrae
 * il concetto di riga e colonna per la {@code Board}.
 */
public class Coordinate implements Cloneable {
    private int row;
    private int col;

    public Coordinate() {
        row = 0;
        col = 0;
    }

    public Coordinate(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

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
