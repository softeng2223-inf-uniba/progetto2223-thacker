package it.uniba.app.new_design.battleship.ship;

import it.uniba.app.utility.Color;
/**
 * La classe {@code Ship} contiene gli attributi e i
 * metodi necessari per istanziare gli esemplari
 * per tipo di nave. Permette anche di
 * controllare se una nave in particolare è stata
 * affondata oppure no.
 */
public final class Ship implements Cloneable {

    private static final String SYMBOL        = "\u22A0";

    private static final String CACCIATORPEDINIERE_STR = "Cacciatorpediniere";
    private static final String INCROCIATORE_STR       = "Incrociatore";
    private static final String CORAZZATA_STR          = "Corazzata";
    private static final String PORTAEREI_STR          = "Portaerei";

    private static final int CACCIATORPEDINIERE_INSTANCES = 4;
    private static final int INCROCIATORE_INSTANCES       = 3;
    private static final int CORAZZATA_INSTANCES          = 2;
    private static final int PORTAEREI_INSTANCES          = 1;

    private static final int CACCIATORPEDINIERE_SIZE = 2;
    private static final int INCROCIATORE_SIZE       = 3;
    private static final int CORAZZATA_SIZE          = 4;
    private static final int PORTAEREI_SIZE          = 5;

    private static final String CACCIATORPEDINIERE_COLOR = "red";
    private static final String INCROCIATORE_COLOR       = "blue";
    private static final String CORAZZATA_COLOR          = "green";
    private static final String PORTAEREI_COLOR          = "yellow";

    private int typeId;
    private int hitsTaken;

    /**
     * Istanzia un tipo di nave attraverso il suo id.
     * Elenco degli id disponibili e tipo associato:
     * <ul>
     * <li>0 Cacciatorpediniere</li>
     * <li>1 Incrociatore</li>
     * <li>2 Corazzata</li>
     * <li>3 Portaerei</li>
     * </ul>
     * <hr>
     * @param id identificativo del tipo di nave
     */
    public Ship(final int id) throws IndexOutOfBoundsException {
        hitsTaken = 0;

        if (id < 0 || id > types.length) {
            throw new IndexOutOfBoundsException();
        } else {
            typeId = id;
        }
    }

    private static class Properties {
        private String name;
        private int size;
        private String colorCode;
        private int maxInstances;

        Properties(final String nm, final int sz,
            final String clr, final int max) {
                    this.name = nm;
                    this.size = sz;
                    this.colorCode = clr;
                    this.maxInstances = max;
        }
    }

    private static Properties[] types = {
        new Properties(CACCIATORPEDINIERE_STR, CACCIATORPEDINIERE_SIZE,
                       CACCIATORPEDINIERE_COLOR, CACCIATORPEDINIERE_INSTANCES),
        new Properties(INCROCIATORE_STR, INCROCIATORE_SIZE, INCROCIATORE_COLOR, INCROCIATORE_INSTANCES),
        new Properties(CORAZZATA_STR, CORAZZATA_SIZE, CORAZZATA_COLOR, CORAZZATA_INSTANCES),
        new Properties(PORTAEREI_STR, PORTAEREI_SIZE, PORTAEREI_COLOR, PORTAEREI_INSTANCES)
    };

    /**
     * Restituisce la lunghezza dell'array {@code Properties},
     * che indica il numero di tipi di nave presenti nel
     * gioco: quattro.
     * @return la lunghezza dell'array Properties.
     */
    public static int getNumberOfTypes() {
        return types.length;
    }

    /**
     * Restituisce il numero massimo di esemplari
     * istanziabili per tipo di nave in una partita.
     * @param id contiene l'indice in cui è
     * posizionata la nave di cui si vuole sapere il
     * numero massimo di esemplari istanziabili.
     * @return numero massimo di esemplari istanziabili
     * del tipo di nave specificato con l'id.
     */
    public static int getMaxInstances(final int id) {
        return types[id].maxInstances;
    }

    /**
     * Restituisce un array di Stringhe contenente
     * i nomi di tutti i tipi di nave presenti nel
     * gioco.
     * @return array di Stringhe con i nomi delle navi.
     */
    public static String[] getTypes() {
        String[] lst = new String[types.length];

        for (int i = 0; i < types.length; i++) {
            lst[i] = types[i].name;
        }

        return lst;
    }

    /* === INSTANCE METHODS === */

    /**
     * Restituisce il nome del tipo di nave
     * istanziato associato al {@code typeId}.
     * @return nome del tipo di nave specifico.
     */
    public String getName() {
        return types[typeId].name;
    }

    /**
     * Restituisce la dimensione della nave
     * istanziata associata al {@code typeId}.
     * @return dimensione della nave specifica.
     */
    public int getSize() {
        return types[typeId].size;
    }

    /**
     * Restituisce il colore assegnato al tipo
     * di nave associato al {@code typeId}.
     * @return colore assegnato al tipo di nave.
     */
    public String getColor() {
        return types[typeId].colorCode;
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
     * Incrementa il valore dell'attributo {@code hitsTaken}
     * di 1 ogni volta che la nave viene colpita.
    */
    public void hit() {
        hitsTaken++;
    }

    /**
     * Restituisce il risultato del confronto tra
     * i colpi subiti da una nave e la sua lunghezza.
     * Serve per verificare se la nave è stata
     * affondata oppure no.
     * @return {@code true} se affondata, {@code false} altrimenti
     */
    public boolean isSunk() {
        return hitsTaken >= types[typeId].size;
    }

    /**
     * Restituisce un clone dell'oggetto nave.
     */
    public Ship clone() {
        Ship clone = null;

        try {
            clone = (Ship) super.clone();
        } catch (CloneNotSupportedException e) { }

        return clone;
    }

    /*
     * Restituisce il simbolo della nave colorato
     * del colore assegnato al tipo di nave specifico
     * istanziato.
    */
    @Override
    public String toString() {
        return getColor() + SYMBOL + Color.getReset();
    }
}

