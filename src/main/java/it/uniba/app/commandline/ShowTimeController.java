package it.uniba.app.commandline;

import it.uniba.app.battleship.controller.TimeController;
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
     */
    String showTime(final Game game) {
        int maxMinute = game.getTime().getTimeLimitMin();
        long minutePassed = TimeController.getInstance().checkTimePassedMillis(game) / CONVERSION_DENOMINATOR;

        if (maxMinute == 0) {
            return "Hai a disposizione un tempo illimitato";
        } else if (!game.isSessionStarted() && maxMinute == 1) {
            return "Quando inizierai la partita avrai a disposizione " + maxMinute + " minuto";
        } else if (!game.isSessionStarted() && maxMinute > 1) {
            return "Quando inizierai la partita avrai a disposizione " + maxMinute + " minuti";
        }  else {
            long remainingMin  = maxMinute - minutePassed;
            return "Numero di minuti trascorsi dall'inizio della partita: " + (minutePassed)
                    + "\nNumero di minuti ancora disponibili per giocare: " + (remainingMin);
        }
    }
}
