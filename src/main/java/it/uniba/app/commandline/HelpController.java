package it.uniba.app.commandline;

/**
 * {@code <<Control>>}<hr>
 * Classe del comando /help.
 * Contiene il codice da eseguire con il comando /help.
 */
public final class HelpController {
    private static final int DELAY = 10;
    private static final String TEXT =
            "Benvenuto in BattleShip!\n\n"
                    + "Scopo del gioco: affondare tutte le navi prima "
                    + "di aver terminato il numero di tentativi fallibili.\n"
                    + "Per giocare, utilizzare i seguenti comandi:\n\n"
                    + "/gioca \n"
                    + "/facile \n"
                    + "/medio \n"
                    + "/difficile \n"
                    + "/mostralivello \n"
                    + "/svelagriglia \n"
                    + "/mostranavi \n"
                    + "/esci";
    private HelpController() { }
    /**
     * Il metodo esegue il codice del comando /help.
     * Stampa una breve descrizione
     * che contiene i comandi di gioco in stile
     * "macchina da scrivere".
     */
    public static void showHelp() {
        for (char c : TEXT.toCharArray()) {
            Output.print(String.valueOf(c));
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                Output.print("InterruptedException: " + e);
                Thread.currentThread().interrupt();
            }
        }
        Output.print("\n");
    }
}
