package it.uniba.app.game;


public class Corazzata extends Ship {
    /**
     * {@code SHIP_SIZE} rappresenta la lunghezza
     * della nave {@code Corazzata}.
     */
    private static final int SHIP_SIZE = 4;
    /**
     * {@code NUMBER_OF_INSTANCES} rappresenta il numero di navi
     * {@code Corazzata} che si possono istanziare
     * durante una partita.
    */
    private static final int NUMBER_OF_INSTANCES = 2;
    /**
     * {@code CORAZZATA_COLOR} contiene il colore scelto
     * per la nave Corazzata che servirà per rappresentare
     * la nave all'interno della {@code Griglia delle Navi}.
     */
    private static final String CORAZZATA_COLOR = "\u001B[32m";
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
    Corazzata() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES);
        name = "Corazzata";
    }
    /**
     * Restituisce il {@code name} della nave {@code Corazzata}.
     * @return nome della nave.
     */
    String getName() {
        return name + "            ";
    }
    /**
     * Restituisce la stringa che contiene il
     * colore del tipo di nave specifico.
     * @return stringa che contiene il colore della nave.
     */
    String getShipColoredSymbol() {
        return CORAZZATA_COLOR;
    }
    /**
     * Restituisce il simbolo comune alle navi colorato
     * con il colore scelto per le navi {@code Corazzata}.
     * Da utilizzare per la stampa della nave nella {@code Griglia
     * delle Navi}.
     * @return simbolo nave {@code Corazzata} colorato.
     */
    public String toString() {
        return CORAZZATA_COLOR + getShipSymbol() + getShipWhiteSymbol();
    }
}
