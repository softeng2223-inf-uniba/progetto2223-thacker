package it.uniba.app.game;
/**
 * La classe astratta 'Ship' è utilizzata come serbatoio
 * di conoscenza per le sue sottoclassi. Contiene gli
 * attributi comuni a tutte le sottoclassi, che verranno
 * ereditati e inizializzati in maniera opportuna.
 * Inoltre, contiene i metodi di accesso agli attributi
 * e il metodo {@code incrementHitsTaken}, che servirà per
 * salvare il numero di colpi subiti da un oggetto {@code nave}.
 */
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
    /**
     * {@code name} contiene il nome della nave rappresentata
     * dalla sottoclasse.
     */
    private String name;
    /**
     * {@code numberOfInstances} indica il numero di
     * istanze massimo per tipo di nave.
     */
    private int numberOfInstances;
    /**
     * {@code hitsTaken} contiene il numero di colpi che una singola
     * nave ha ricevuto. Deve essere incrementato ad ogni colpo
     * andato a segno.
     */
    private int hitsTaken;
    /**
     * Unico costruttore della classe che sarà invocato
     * dalle sottoclassi, in cui inizializziamo attributi
     * da ereditare.
     * @param size lunghezza della nave.
     * @param val numero di istanze di una specializzazione di nave.
     * @param nome nome del tipo di nave specifico.
     */
    public Ship(final int size, final int val, final String nome) {
        shipSize = size;
        numberOfInstances = val;
        hitsTaken = 0;
        name = nome;
    }
    /**
     * Restituisce il nome della nave presa
     * in considerazione. Metodo astratto che verrà
     * implementato nelle sottoclassi.
     * @return il nome del tipo di nave
     * su cui viene chiamata la funzione.
    */
    abstract String getName();
    /**
     * Restituisce la stringa che contiene il
     * colore del tipo di nave specifico.
     * Metodo astratto che verrà implementato nelle
     * sottoclassi.
     * @return stringa che contiene il colore della nave.
     */
    abstract String getShipColoredSymbol();
    /**
     * Restituisce il numero di tipi
     * di nave presenti.
     * @return numero di tipi di nave.
     */
    public int getShipsNumber() {
        return SHIPS_NUMBER;
    }
    /**
     * Restituisce la dimensione
     * della nave presa in considerazione.
     * @return lunghezza nave.
     */
    public int getShipSize() {
        return shipSize;
    }
    /**
     * Restituisce il numero di istanze
     * massimo del tipo di nave.
     * @return numero di istanze massimo per tipo di nave.
     */
    public int getNumberOfInstances() {
        return numberOfInstances;
    }
    /**
     * Restituisce il numero di colpi
     * che ha subito la singola nave.
     * @return numero di colpi subiti da un oggetto nave.
    */
    public int getHitsTaken() {
        return hitsTaken;
    }
    /**
     * Restituisce il simbolo quadrato condiviso da
     * tutti i tipi di nave.
     * @return Stringa che contiene il simbolo della nave scelto.
     */
    public String getShipSymbol() {
        return SHIPS_SYMBOL;
    }
    /**
     * Restituisce la stringa che contiene
     * il colore neutro da assegnare alla nave
     * dopo la stampa.
     * @return stringa con colore neutro.
     */
    public String getShipWhiteSymbol() {
        return SYMBOL_WHITE_COLOR;
    }
    /**
     * Incrementa il valore dell'attributo {@code hitsTaken}
     * di 1 ogni volta che la nave viene colpita.
    */
    public void incrementHitsTaken() {
        hitsTaken++;
    }
}
