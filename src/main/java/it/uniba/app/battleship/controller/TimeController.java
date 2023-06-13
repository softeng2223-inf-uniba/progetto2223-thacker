package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Time;

/** {@code <<Control>>}<hr>
 * La classe {@code TimeController} fornisce i servizi
 * per gestire il comando {@code /tempo}, che permette di
 * impostare il numero di minuti disponibile in una partita
 * per giocare.
 */
public final class TimeController {

    private static final int SECOND = 60;
    private static final int MILLISECONDS = 1000;

    private static class Holder {
        private static final TimeController INSTANCE = new TimeController();
    }

    private TimeController() { };

    public static TimeController getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Permette di settare il limite di tempo e il
     * tempo d'inizio in millisecondi.
     * @param time contiene l'istanza di Time da
     * aggiornare con i valori corretti.
     */
    public void setTime(final Time time) {
        time.setTimeLimitMillis(time.getTimeLimitMin() * SECOND * MILLISECONDS);
        time.setStartTimeMill(System.currentTimeMillis());
    }

    /**
     * Permette di settare il numero di minuti a disposizione
     * nella partita per giocare.
     * @param timeSet contiene i minuti a disposizione per giocare.
     */
    public void setTimeLimit(final Time time, final int timeSet) {
        time.setTimeLimitMin(timeSet);
    }

    /**
     * Permette di controllare quanto tempo è trascorso
     * dall'inizio della partita all'istante in cui
     * viene chiamato il metodo.
     * @param game contiene i dati della partita in corso.
     * @return il tempo trascorso in millisecondi.
     */
    public long checkTimePassedMillis(final Game game) {
        return game.getTime().getCurrentTimeMillis() - game.getTime().getStartTimeMillis();
    }

    /**
     * Permette di controllare se il tempo a dispozione
     * per giocare è terminato, assicurandosi che il limite di tempo
     * sia stato impostato e che la partita sia cominciata.
     * @param game contiene i dati della partita in corso.
     * @return {@code true} se il tempo a dispozione è finito / {@code false} in caso contrario.
     */
    public boolean isTimeOver(final Game game) {
        if ((checkTimePassedMillis(game) > game.getTime().getTimeLimitMill())
                && (game.getTime().getTimeLimitMin() != 0) && game.isSessionStarted()) {
            return true;
        }
        return false;
    }

}
