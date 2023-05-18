package it.uniba.app.utility.help;

/**
 * Classe principale. Contiene le funzioni che stampa a video il file di testo
 * contenente le istruzioni per l'utilizzo del programma.
 */
public final class Help {
    public static final int SLEEP_TIME = 1;
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
