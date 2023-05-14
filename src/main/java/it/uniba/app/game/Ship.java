package it.uniba.app.game;

public abstract class Ship {

    private static final int SHIPS_NUMBER = 4;

    private static final String SHIPS_SYMBOL = "\u22A0";

    private static final String SYMBOL_WHITE_COLOR = "\u001B[0m";

    private int shipSize;

    private int numberOfInstances;

    private int hitsTaken;

    public Ship(final int size, final int val) {
        shipSize = size;
        numberOfInstances = val;
        hitsTaken = 0;
    }

    abstract String getName();

    abstract String getShipColoredSymbol();

    public int getShipsNumber() {
        return SHIPS_NUMBER;
    }

    public int getShipSize() {
        return shipSize;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public int getHitsTaken() {
        return hitsTaken;
    }

    public String getShipSymbol() {
        return SHIPS_SYMBOL;
    }

    public String getShipWhiteSymbol() {
        return SYMBOL_WHITE_COLOR;
    }

}
