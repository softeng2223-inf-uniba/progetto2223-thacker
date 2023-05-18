package it.uniba.app.utility.help;

import it.uniba.app.utility.PrintHandler;

/**
 * Classe principale. Contiene le funzioni che stampa a video il file di testo
 * contenente le istruzioni per l'utilizzo del programma.
 */
public final class Help {
    /**
     * {@code TITLE_ROWS} contiene il numero di
     * elementi dell'array di Stringhe che contiene
     * il titolo {@code BattleShip}.
     */
    private static final int TITLE_ROWS = 6;
    /**
     * {@code TITLE_DELAY} contiene la costante
     * da passare come input al metodo {@code Thread.sleep()}
     * per ottenere un effetto di stampa "retrò" in stile
     * "cascata".
     */
    private static final int TITLE_DELAY = 100;
    /**
     * {@code TEXT_DELAY} contiene la costante
     * da passare come input al metodo {@code Thread.sleep()}
     * per ottenere un effetto di stampa "retrò" in stile
     * "macchina da scrivere".
     */
    private static final int TEXT_DELAY = 12;

    /**
     * {@code TITLE} contiene il nome
     * del gioco in stile ASCII, da stampare
     * come titolo all'apertura dell'Help e
     * dell'applicazione.
     */
    private static final String[] TITLE = new String[] {
        "██████╗  █████╗ ████████╗████████╗██╗     ███████╗███████╗██╗  ██╗██╗██████╗",
        "██╔══██╗██╔══██╗╚══██╔══╝╚══██╔══╝██║     ██╔════╝██╔════╝██║  ██║██║██╔══██╗",
        "██████╔╝███████║   ██║      ██║   ██║     █████╗  ███████╗███████║██║██████╔╝",
        "██╔══██╗██╔══██║   ██║      ██║   ██║     ██╔══╝  ╚════██║██╔══██║██║██╔═══╝",
        "██████╔╝██║  ██║   ██║      ██║   ███████╗███████╗███████║██║  ██║██║██║",
        "╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝╚═╝"
    };

    /**
     * {@code TEXT} contiene una descrizione concisa
     * seguita dalla lista di comandi disponibili.
     */
    private static final String TEXT =
    "Benvenuto in BattleShip!\n\n"
    + "Scopo del gioco: affondare tutte le navi prima di aver terminato il numero di tentativi fallibili.\n"
    + "Per giocare, utilizzare i seguenti comandi:\n\n"
    + "/gioca \n"
    + "/facile \n"
    + "/medio \n" 
    + "/difficile \n"
    + "/mostralivello \n"
    + "/svelagriglia \n"
    + "/mostranavi \n"
    + "/esci";

    private Help() { };

    private static void printTitle() {
        for (int i = 0; i < TITLE_ROWS; i++) {
            PrintHandler.println(TITLE[i]);
            try {
                Thread.sleep(TITLE_DELAY);
            } catch (InterruptedException err) {
                PrintHandler.println("InterruptedException: " + err);
            }
        }
        PrintHandler.println("\n");
    }

    /**
     * Classe principale, stampa a video il file di testo contenente le istruzioni.
    *
    */
    public static void print() {
        try {
            for (int i = 0; i < TITLE.length(); i++) {
                System.out.print(TITLE.charAt(i));
                Thread.sleep(SLEEP_TIME);
            }
            for (int i = 0; i < TEXT.length(); i++) {
                System.out.print(TEXT.charAt(i));
                Thread.sleep(SLEEP_TIME);
            }
        } catch (InterruptedException err) {
            System.out.println("Errore: " + err);
        }
    }
}
