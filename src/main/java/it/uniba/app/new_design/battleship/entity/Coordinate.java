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
}


