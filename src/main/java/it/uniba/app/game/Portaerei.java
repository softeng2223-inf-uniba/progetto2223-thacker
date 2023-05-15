package it.uniba.app.game;

public class Portaerei extends Ship {
    /**
     * {@code SHIP_SIZE} rappresenta la lunghezza
     * della nave {@code Portaerei}.
     */
    private static final int SHIP_SIZE = 5;
    /**
     * {@code NUMBER_OF_INSTANCES} rappresenta il numero di navi
     * {@code Portaerei} che si possono istanziare
     * durante una partita.
    */
    private static final int NUMBER_OF_INSTANCES = 1;
    /**
     * {@code PORTAEREI_COLOR} contiene il colore scelto
     * per la nave Corazzata che servirà per rappresentare
     * la nave all'interno della {@code Griglia delle Navi}.
     */
    private static final String PORTAEREI_COLOR = "\u001B[33m";

    private String name;

    Portaerei() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Portaerei";
    }

    String getName() {
        return name + "            ";
    }

    String getShipColoredSymbol() {
        return PORTAEREI_COLOR;
    }

    public String toString() {
        return PORTAEREI_COLOR + getShipSymbol() + getShipWhiteSymbol();
    }
}

