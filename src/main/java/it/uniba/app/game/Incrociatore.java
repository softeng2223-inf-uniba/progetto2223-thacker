package it.uniba.app.game;

public class Incrociatore extends Ship {

    private static final int SHIP_SIZE = 3;

    private static final int NUMBER_OF_INSTANCES = 3;

    private static final String INCROCIATORE_COLOR = "\u001B[34m";

    private String name;

    Incrociatore() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Incrociatore";
    }

    String getName() {
        return name;
    }

    String getShipColoredSymbol() {
        return INCROCIATORE_COLOR;
    }

    public String toString() {
        return INCROCIATORE_COLOR + getShipSymbol() + getShipWhiteSymbol();
    }
}
