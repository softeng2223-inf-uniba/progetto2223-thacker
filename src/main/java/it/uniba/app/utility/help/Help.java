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
    + "   ╚═╝  ╚═╝╚══════╝ ╚═════╝  ╚═════╝ ╚══════╝╚══════╝    ╚═════╝ ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝" + "\n\n";

    private static final String TEXT =

    "Il giocatore avrà a disposizione due griglie di dimensione 10x10,"
    + "una dove saranno disposte le navi in maniera casuale, e l'altra dove potrà"
    + "indicare le coordinate della casella che ha intenzione di colpire." + "\n\n"
    + "Prima di cominciare la partita, il giocatore dovrà scegliere il livello di difficoltà di quest'ultima." + "\n\n"
    + "L'impostazione della difficoltà potrà essere effettuata tramite i comandi" + "\n\n"
    + "- /facile," + "\n\n"
    + "- /medio," + "\n\n"
    + "- /difficile." + "\n\n"
    + "Il livello facile prevede un numero massimo di tentativi"
    + "fallibili pari a 50, il livello medio 30 e il livello difficile 10." + "\n\n"
    + "Per mostrare il livello di difficoltà durante la partita, il giocatore deve "
    + "digitare il comando /mostralivello.Una volta impostata la difficoltà, il giocatore potrà "
    + "cominciare una nuova partita digitando il comando /gioca." + "\n\n"
    + "Con il comando /mostranavi, il giocatore visualizzerà l'elenco delle navi "
    + "a disposizione, in particolare: il nome, la dimensione in quadratini e "
    + "il numero di esemplari istanziabili." + "\n\n"
    + "Nel caso si riscontrassero delle difficoltà nel colpire le navi dell'avversario, "
    + "il giocatore può invocare il comando /svelagriglia per scoprire  la griglia dell'avversario, "
    + "ovvero la griglia con le navi posizionate." + "\n\n"
    + "" + "\n";
    /**
     * <p>.
    *
    */
    private Help() { };
    /**
     * <p>.
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