package it.uniba.app.battleship.ship;

public final class Ship implements Cloneable {

    private static final String SYMBOL        = "\u22A0";
    private static final String DEFAULT_COLOR = "\u001B[0m";

    private static final String CACCIATORPEDINIERE_STR  = "Cacciatorpediniere";
    private static final String INCROCIATORE_STR    = "Incrociatore";
    private static final String CORAZZATA_STR = "Corazzata";
    private static final String PORTAEREI_STR    = "Portaerei";

    private static final int CACCIATORPEDINIERE_INSTANCES  = 4;
    private static final int INCROCIATORE_INSTANCES    = 3;
    private static final int CORAZZATA_INSTANCES = 2;
    private static final int PORTAEREI_INSTANCES    = 1;

    private static final int CACCIATORPEDINIERE_SIZE  = 2;
    private static final int INCROCIATORE_SIZE    = 3;
    private static final int CORAZZATA_SIZE = 4;
    private static final int PORTAEREI_SIZE    = 5;
}

