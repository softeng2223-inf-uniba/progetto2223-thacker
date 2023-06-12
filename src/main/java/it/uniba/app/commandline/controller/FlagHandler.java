package it.uniba.app.commandline.controller;

import it.uniba.app.battleship.controller.HelpController;
import it.uniba.app.commandline.Output;

/**
 * Gestisce i parametri passati all'avvio dell'applicazione (flags).
 */
public final class FlagHandler {
    private FlagHandler() { }

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
    public static boolean execute(final String[] args) {
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
