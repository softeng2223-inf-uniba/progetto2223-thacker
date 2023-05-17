package it.uniba.app.utility.help;

/**
 * <p>
 * Classe principale. Contiene le funzioni che stampa a video il file di testo
 * contenente le istruzioni per l'utilizzo del programma.
 */
public final class Help {
    public static final int SLEEP_TIME = 1;
    private static final String  TITLE =
      "   ██████╗ ███████╗ ██████╗  ██████╗ ██╗     ███████╗    ██████╗ ██╗    ██████╗  █████╗ ███████╗███████╗" + "\n"
    + "   ██╔══██╗██╔════╝██╔════╝ ██╔═══██╗██║     ██╔════╝    ██╔══██╗██║    ██╔══██╗██╔══██╗██╔════╝██╔════╝" + "\n"
    + "   ██████╔╝█████╗  ██║  ███╗██║   ██║██║     █████╗      ██║  ██║██║    ██████╔╝███████║███████╗█████╗" + "\n"
    + "   ██╔══██╗██╔══╝  ██║   ██║██║   ██║██║     ██╔══╝      ██║  ██║██║    ██╔══██╗██╔══██║╚════██║██╔══╝" + "\n"
    + "   ██║  ██║███████╗╚██████╔╝╚██████╔╝███████╗███████╗    ██████╔╝██║    ██████╔╝██║  ██║███████║███████╗" + "\n"
    + "   ╚═╝  ╚═╝╚══════╝ ╚═════╝  ╚═════╝ ╚══════╝╚══════╝    ╚═════╝ ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝" + "\n";

    private Help() { };
    /**
     * <p>.
    *
    */
    public static void printText() {
        for (int i = 0; i < TITLE.length(); i++) {
            System.out.print(TITLE.charAt(i));
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException err) {
                System.out.println("Errore: " + err);
            }
        }
    }
}