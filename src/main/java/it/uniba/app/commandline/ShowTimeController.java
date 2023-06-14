package it.uniba.app.commandline;

import it.uniba.app.battleship.GameController;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.exception.SessionNotStartedException;

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
     * Visualizza le informazioni legate al tempo.
     * Se vi è una sessione in corso, mostra il tempo di gioco trascorso e rimanente.
     * Se non vi è una sessione in corso, mostra il tempo limite impostato per la
     * prossima sessione di gioco.
     * @param game contiene i dati relativi alla sessione di gioco.
     */
    void showTime(final Game game) {
        int maxMinute = game.getTime().getTimeLimitMin();

        try {
            long minutePassed = GameController.getInstance().checkTimePassedMillis(game) / CONVERSION_DENOMINATOR;
            Output.printSessionTime(maxMinute, minutePassed);
        } catch (SessionNotStartedException e) {
            // se non vi è una sessione in corso
            Output.printTimeSetting(maxMinute);
        }
    }
}
