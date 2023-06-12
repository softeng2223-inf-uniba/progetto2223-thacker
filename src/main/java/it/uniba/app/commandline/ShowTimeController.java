package it.uniba.app.commandline;

import it.uniba.app.battleship.controller.TimeController;
import it.uniba.app.battleship.entity.Game;

/**
 * {@code <<control>}<br>
 * Fornisce servizi per formattare il tempo di gioco in formato stringa.
 */
public final class ShowTimeController {
    private static final int CONVERSION_DENOMINATOR = 60000;

    private ShowTimeController() { }
    /**
     * Restituisce una oggetto di tipo {@code String} che
     * contiene il numero di minuti disponibile per giocare
     * durante una partita in quel momento.
     * Permette d'implementare il comando {@code /mostratempo}.
     * @param game contiene i dati relativi alla sessione di gioco.
     */
    static String showTime(final Game game) {
        int maxMinute = game.getTime().getTimeLimitMin();
        long minutePassed = TimeController.checkTimePassedMillis(game) / CONVERSION_DENOMINATOR;

        if (maxMinute == 0) {
            return "Hai a disposizione un tempo illimitato";
        } else if (!game.isSessionStarted()) {
            return "Quando inizierai la partita avrai a disposizione " + maxMinute + " minuti";
        } else {
            long remainingMin  = maxMinute - minutePassed;
            return "Numero di minuti trascorsi dall'inizio della partita: " + (minutePassed)
                    + "\nNumero di minuti ancora disponibili per giocare: " + (remainingMin);
        }
    }
}