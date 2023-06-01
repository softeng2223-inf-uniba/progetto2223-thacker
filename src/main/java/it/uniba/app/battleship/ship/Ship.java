package it.uniba.app.battleship.ship;

public final class Ship implements Cloneable {

    private static final String SYMBOL        = "\u22A0";
    private static final String DEFAULT_COLOR = "\u001B[0m";

    private static final String DESTROYER_STR  = "Cacciatorpediniere";
    private static final String CRUISER_STR    = "Incrociatore";
    private static final String BATTLESHIP_STR = "Corazzata";
    private static final String FLATTOP_STR    = "Portaerei";

    private static final int DESTROYER_INSTANCES  = 4;
    private static final int CRUISER_INSTANCES    = 3;
    private static final int BATTLESHIP_INSTANCES = 2;
    private static final int FLATTOP_INSTANCES    = 1;
}

