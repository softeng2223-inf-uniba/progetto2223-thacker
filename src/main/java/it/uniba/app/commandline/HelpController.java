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
                    + "/help \n"
                    + "/gioca \n"
                    + "/tempo @param \n"
                    + "/tentativi @param \n"
                    + "/mostratentativi \n"
                    + "/facile \n"
                    + "/facile @param \n"
                    + "/medio \n"
                    + "/medio @param \n"
                    + "/difficile \n"
                    + "/difficile @param \n"
                    + "/standard \n"
                    + "/large \n"
                    + "/extralarge \n"
                    + "/abbandona \n"
                    + "/mostratempo \n"
                    + "/mostralivello \n"
                    + "/mostragriglia \n"
                    + "/mostranavi \n"
                    + "/esci \n"
                    + "/abbandona \n";


    private static class Holder {
        private static final HelpController INSTANCE = new HelpController();
    }

    private HelpController() { };

    public static HelpController getInstance() {
            return Holder.INSTANCE;
    }

    /**
     * Il metodo esegue il codice del comando /help.
     * Stampa una breve descrizione
     * che contiene i comandi di gioco in stile
     * "macchina da scrivere".
     */
    void showHelp() {
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
