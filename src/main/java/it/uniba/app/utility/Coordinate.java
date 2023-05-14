package it.uniba.app.utility;

/**
 * JavaDoc momentaneo. La classe {@code Coordinate} astrae
 * il concetto di riga e colonna per la {@code Board}.
 */
public class Coordinate {
    private int row;
    private int col;

    public Coordinate(){
        row = 0;
        col = 0;
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
}
