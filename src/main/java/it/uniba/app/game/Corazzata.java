package it.uniba.app.game;


public class Corazzata extends Ship {

    private static final int SHIP_SIZE = 4;

    private static final int NUMBER_OF_INSTANCES = 2;

    private static final String CORAZZATA_COLOR = "\u001B[32m";

    private String name;

    Corazzata() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Corazzata";
    }
 
    String getName() {
        return name;
    }

    String getShipColoredSymbol() {
        return CORAZZATA_COLOR;
    }

    public String toString() {
        return CORAZZATA_COLOR + getShipSymbol() + getShipWhiteSymbol();
    }
}
