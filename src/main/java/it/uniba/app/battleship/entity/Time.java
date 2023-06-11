package it.uniba.app.battleship.entity;

/**
 * La classe {@code Time} permette di conservare
 * i valori del tempo, utilizzati dal {@code TimeController},
 * necessari per poter aggiungere a
 * <i>Battleship</i> la funzionalità del comando
 * {@code /tempo}.
 */
public class Time {

    private static long timeLimitMinute;
    private static long startTimeMill;
    private static long timeLimitMilliseconds;
    private static long currentTimeMill;

    /**
     * Costruttore pubblico che permette di istanziare un oggetto
     * {@code Time} con attributi inizializzati con valori di default.
     */
    public Time() { };

    public void setTimeLimitMin(final long time) {
        timeLimitMinute = time;
    }

    public long getTimeLimitMin() {
        return timeLimitMinute;
    }

    public void setTimeLimitMill(final Long time) {
        timeLimitMilliseconds = time;
    }

    public void setStartTimeMill(final Long time) {
        startTimeMill = time;
    }

    public long getStartTimeMill() {
        return startTimeMill;
    }

    public long getTimeLimitMill() {
        return timeLimitMilliseconds;
    }

    public long getCurrentTimeMill() {
        currentTimeMill = System.currentTimeMillis();
        return currentTimeMill;
    }
}
