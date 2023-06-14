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
                    + "/help: Mostra l'elenco dei comandi.\n"
                    + "/gioca: Inizia una nuova partita. \n"
                    + "/tempo @param: Imposta il tempo di gioco a @param minuti\n"
                    + "/tentativi @param: Iposta la difficoltà a custom con tentativi fallibili a @param. \n"
                    + "/mostratentativi: Visualizza i tentativi effettuati, quelli falliti"
                    + "e quelli fallibili durante la partita.\n"
                    + "/facile @param: Imposta il livello di difficoltà a facile. \n"
                    + "/facile @param: Imposta la difficoltà a facile con tentativi fallibili a @param. \n"
                    + "/medio Imposta il livello di difficoltà a medio. \n"
                    + "/medio @param: Imposta la difficoltà a medio con tentativi fallibili a @param. \n"
                    + "/difficile Imposta il livello di difficoltà a difficile. \n"
                    + "/difficile @param: Imposta la difficoltà a difficile con tentativi fallibili a @param.\n"
                    + "/standard: Imposta la dimensione della griglia di gioco su standard 10x10.\n"
                    + "/large: Imposta la dimensione della griglia di gioco su large 18x18.\n"
                    + "/extralarge: Imposta la dimensione della griglia di gioco su extralarge 26x26.\n"
                    + "/mostratempo: Visualizza i minuti trascorsi e quelli rimanenti durante la partita.\n"
                    + "/mostralivello: Mostra il livello di difficoltà durante la partita.\n"
                    + "/mostragriglia: Mostra a schermo la griglia con i colpi andati a buon fine, "
                    + "nel senso che hanno colpito una nave avversaria. \n"
                    + "/svelagriglia: Mostra la griglia dell'avversario.\n"
                    + "/mostranavi: Mostra le informazioni sulle navi, inclusi nomi,"
                    + "rappresentazione e quantit\u00E0 disponibili.\n"
                    + "/esci: Termina il programma se arriva una conferma dall'utente.\n"
                    + "/abbandona: Abbandona la partita corrente.\n";


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
