package it.uniba.app.game;

public class Cacciatorpediniere extends Ship {

    private static final int SHIP_SIZE = 2;

    private static final int NUMBER_OF_INSTANCES = 4;

    private static final String CACCIATORPEDINIERE_COLOR = "\u001B[31m";

    private String name;

    Cacciatorpediniere() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Cacciatorpediniere";
    }

}

