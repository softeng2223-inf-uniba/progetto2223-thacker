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

    private static final String CACCIATORPEDINIERE_COLOR  = "\u001B[31m";
    private static final String INCROCIATORE_COLOR    = "\u001B[34m";
    private static final String CORAZZATA_COLOR = "\u001B[32m";
    private static final String PORTAEREI_COLOR    = "\u001B[33m";

    private int typeId;
    private int hitsTaken;

    /**
     * TODO javadoc.
     */
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
     * TODO javadoc.
     * @return
     */
    public static int getNumberOfTypes() {
        return types.length;
    }

    /**
     * TODO javadoc.
     * @param id
     * @return
     */
    public static int getMaxIstances(final int id) {
        return types[id].maxInstances;
    }

    /**
     * TODO javadoc.
     * @return
     */
    public static String[] getTypes() {
        String[] lst = new String[types.length];

        for (int i = 0; i < types.length; i++) {
            lst[i] = types[i].name;
        }

        return lst;
    }

}

