package it.uniba.app.new_design.battleship.entity;

public class Coordinate implements Cloneable {

    private int col;

    private int row;

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


