package it.uniba.app.game;
/**
 * Specializzazione della classe {@code Ship} che rappresenta
 * la nave {@code Portaerei} di lunghezza 5, di
 * cui possiamo avere 1 istanze.
 * Contiene i metodi di accesso e i metodi necessari per la stampa
 * delle informazioni relative alla nave e per la stampa
 * della nave all'interno della {@code Griglia delle Navi}.
 */
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
    /**
     * {@code SHIP_NAME} contiene il nome della nave rappresentata
     * da questa classe.
     */
    private static final String SHIP_NAME = "Portaerei";
    /**
     * Costruttore della classe che chiama il costruttore
     * della superclasse per inizializzare gli attributi
     * ereditati.
     */
    Portaerei() {
        super(SHIP_SIZE, NUMBER_OF_INSTANCES, SHIP_NAME);
    }
    /**
     * Restituisce il {@code name} della nave {@code Portaerei}.
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
        return PORTAEREI_COLOR;
    }
    /**
     * Restituisce il simbolo comune alle navi colorato
     * con il colore scelto per le navi {@code Portaerei}.
     * Da utilizzare per la stampa della nave nella {@code Griglia
     * delle Navi}.
     * @return simbolo nave {@code Portaerei} colorato.
     */
    public String toString() {
        return PORTAEREI_COLOR + getShipSymbol() + getShipWhiteSymbol();
    }
}

