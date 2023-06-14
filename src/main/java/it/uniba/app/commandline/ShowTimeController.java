package it.uniba.app.commandline;

import it.uniba.app.battleship.GameController;
import it.uniba.app.battleship.entity.Game;

/**
 * {@code <<control>}<br>
 * Fornisce servizi per formattare il tempo di gioco in formato stringa.
 */
public final class ShowTimeController {
    private static final int CONVERSION_DENOMINATOR = 60000;

    private static class Holder {
            private static final ShowTimeController INSTANCE = new ShowTimeController();
    }

    private ShowTimeController() { };

    /**
     * Fornisce l'istanza del gestore della visualizzazione del tempo.
     * @return istanza di ShowTimeController
     */
    public static ShowTimeController getInstance() {
            return Holder.INSTANCE;
    }

    /**
     * Restituisce una oggetto di tipo {@code String} che
     * contiene il numero di minuti disponibile per giocare
     * durante una partita in quel momento.
     * Permette d'implementare il comando {@code /mostratempo}.
     * @param game contiene i dati relativi alla sessione di gioco.
     * @return messaggio con informazioni legate al tempo
     */
    String showTime(final Game game) {
        String message = new String();

        int maxMinute = game.getTime().getTimeLimitMin();
        long minutePassed = GameController.getInstance().checkTimePassedMillis(game) / CONVERSION_DENOMINATOR;

        // se una sessione di gioco è in corso
        if (game.isSessionStarted()) {
            message = "Numero di minuti trascorsi dall'inizio della partita: "
                + minutePassed;
            message += "\nNumero di minuti ancora disponibili per giocare: ";

            if (maxMinute > 0) {
                long remainingMin  = maxMinute - minutePassed;
                message += remainingMin;
            } else {
                message += "infiniti";
            }
            return message;
        }
        // se non vi sono sessioni in corso
        switch (maxMinute) {
            case 0:
                message = "Hai a disposizione un tempo illimitato";
                break;
            case 1:
                message = "Quando inizierai la partita avrai a disposizione "
                + maxMinute
                + " minuto";
                break;
            default:
                message = "Quando inizierai la partita avrai a disposizione "
                + maxMinute
                + " minuti";
                break;
        }
        return message;
    }
}
