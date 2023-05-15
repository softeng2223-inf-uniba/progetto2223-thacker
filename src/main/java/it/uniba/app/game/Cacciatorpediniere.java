package it.uniba.app.game;

public class Cacciatorpediniere extends Ship {
    /**
     * {@code SHIP_SIZE} rappresenta la lunghezza
     * della nave Cacciatorpediniere.
     */
    private static final int SHIP_SIZE = 2;
    /**
     * {@code NUMBER_OF_INSTANCES} rappresenta il numero di navi
     * {@code Cacciatorpediniere} che si possono istanziare
     * durante una partita.
    */
    private static final int NUMBER_OF_INSTANCES = 4;
    /**
     * {@code CACCIATORPEDINIERE_COLOR} contiene il colore scelto
     * per la nave Corazzata che servirà per rappresentare
     * la nave all'interno della {@code Griglia delle Navi}.
     */
    private static final String CACCIATORPEDINIERE_COLOR = "\u001B[31m";
    /**
     * {@code name} contiene il nome della nave rappresentata
     * da questa classe.
     */
    private String name;
    /**
     * Costruttore della classe che chiama il costruttore
     * della superclasse per inizializzare gli attributi
     * ereditati e in piu' inizializza l'attributo
     * {@code name} della nave.
     */
    Cacciatorpediniere() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Cacciatorpediniere";
    }

    String getName() {
        return name + "   ";
    }

    String getShipColoredSymbol() {
        return CACCIATORPEDINIERE_COLOR;
    }

    public String toString() {
        return CACCIATORPEDINIERE_COLOR + getShipSymbol() + getShipWhiteSymbol();
    }

}

