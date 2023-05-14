package it.uniba.app.game;

public class Portaerei extends Ship {

    private static final int SHIP_SIZE = 5;

    private static final int NUMBER_OF_INSTANCES = 1;

    private static final String PORTAEREI_COLOR = "\u001B[33m";

    private String name;

    Portaerei() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Portaerei";
    }

    String getName() {
        return name;
    }

    String getShipColoredSymbol() {
        return PORTAEREI_COLOR;
    }

}

