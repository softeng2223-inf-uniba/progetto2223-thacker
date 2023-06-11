package it.uniba.app.commandline.controller;

import it.uniba.app.battleship.controller.HelpController;

/* CONTROL CLASS */

/**
 * Gestisce i parametri passati all'avvio dell'applicazione (flags).
 */
public final class FlagHandler {
    private static final String MSG_NOT_RECOGNISED = "Flag non riconosciuto";
    private static final String MSG_TOO_MANY = "Inseriti troppi flag";

    private FlagHandler() { }

    /**
     * Elabora ed esegue una sequenza di flag.
     *
     * I flag gestiti attualmente sono:
     * <ul>
     * <li>{@code --help} mostra l'elenco dei comandi</li>
     * <li>{@code -h} mostra l'elenco dei comandi</li>
     * </ul>
     * @param args flag da elaborare
     */
    public static void execute(final String[] args) {
        if (args.length == 0) {
            return;
        }

        if (args.length > 1) {
            System.out.println(MSG_TOO_MANY);
            return;
        }

        if (args[0].equals("-h") || args[0].equals("--help")) {
            HelpController.showHelp();
        } else {
            System.out.println(MSG_NOT_RECOGNISED);
        }
    }
}
