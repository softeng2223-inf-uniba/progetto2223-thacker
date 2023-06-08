package it.uniba.app.new_design.battleship.controller;
/**
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
        for (int i = 0; i < TEXT.length(); i++) {
            System.out.print(String.valueOf(TEXT.charAt(i)));
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException err) {
                System.out.println("InterruptedException: " + err);
            }
        }
        System.out.println("\n");
    }
}
