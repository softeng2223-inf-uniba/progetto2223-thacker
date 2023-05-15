package it.uniba.app.game;
/**
 * Specializzazione della classe {@code Ship} che rappresenta
 * la nave {@code Corazzata} di lunghezza 4, di
 * cui possiamo avere 2 istanze.
 * Contiene i metodi di accesso e i metodi necessari per la stampa
 * delle informazioni relative alla nave e per la stampa
 * della nave all'interno della {@code Griglia delle Navi}.
 */
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
     * {@code SHIP_NAME} contiene il nome della nave rappresentata
     * da questa classe.
     */
    private static final String SHIP_NAME = "Corazzata";
    /**
     * Costruttore della classe che chiama il costruttore
     * della superclasse per inizializzare gli attributi
     * ereditati.
     */
    Corazzata() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES, SHIP_NAME);
    }
    /**
     * Restituisce il {@code name} della nave {@code Corazzata}.
     * @return nome della nave.
     */
    String getName() {
        return SHIP_NAME;
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
