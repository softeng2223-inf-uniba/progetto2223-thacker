package it.uniba.app.commandline;

/**
 * {@code <<Control>>}<hr>
 * Gestisce i parametri passati all'avvio dell'applicazione (flags).
 */
public final class FlagHandler {
    private static class Holder {
        private static final FlagHandler INSTANCE = new FlagHandler();
    }

    private FlagHandler() { };

    /**
     * Fornisce l'istanza del gestore dei flag.
     * @return istanza di FlagHandler
     */
    public static FlagHandler getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Elabora ed esegue una sequenza di flag.
     * <p>
     * I flag gestiti attualmente sono:
     * <ul>
     * <li>{@code --help} mostra l'elenco dei comandi</li>
     * <li>{@code -h} mostra l'elenco dei comandi</li>
     * </ul>
     * @param args flag da elaborare
     */
    public boolean execute(final String[] args) {
        if (args.length == 0) {
            return false;
        }
        if (args.length == 1) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                HelpController.showHelp();
                return true;
            }
        }
        Output.printFlagNotRecognised(args);
        return false;
    }
}
