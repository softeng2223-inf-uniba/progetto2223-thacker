package it.uniba.app.game;

public abstract class Ship {
    /**
     * {@code SHIPS_NUMBER} rappresenta il numero di tipi di nave
     * che si possono trovare all'interno del gioco.
     */
    private static final int SHIPS_NUMBER = 4;
    /**
     * {@code SHIPS_SYMBOL} contiene il valore in formato stringa
     * per mostrare a schermo il simbolo nave ⛝.
     */
    private static final String SHIPS_SYMBOL = "\u22A0";
    /**
     * {@code SYMBOL_WHITE_COLOR} contiene il colore neutro del
     * simbolo nave.
     */
    private static final String SYMBOL_WHITE_COLOR = "\u001B[0m";
    /**
     * {@code shipSize} rappresenta la dimensione di ogni tipo di nave.
     */
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

    public void incrementHitsTaken() {
        hitsTaken++;
    }
}
