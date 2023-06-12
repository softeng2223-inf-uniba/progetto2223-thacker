package it.uniba.app.battleship.entity;

/**
 * La classe {@code Time} permette di conservare
 * i valori del tempo, utilizzati dal {@code TimeController},
 * necessari per poter aggiungere a
 * <i>Battleship</i> la funzionalità del comando
 * {@code /tempo}.
 */
public class Time implements Cloneable {
    private int timeLimitMinute;
    private long startTimeMillis;
    private long timeLimitMillisSeconds;

    /**
     * Costruttore pubblico che permette d'istanziare un oggetto
     * {@code Time} con attributi inizializzati con valori di default.
     */
    public Time() { };

    /**
     * Permette d'impostare il limite di tempo
     * in minuti inserito dall'utente.
     * @param time tempo in minuti scelto dall'utente.
     */
    public void setTimeLimitMin(final int time) {
        timeLimitMinute = time;
    }

    /**
     * Restituisce il numero di minuti a disposizione
     * per giocare scelto dall'utente.
     * @return {@code timeLimitMinute} tempo in minuti.
     */
    public int getTimeLimitMin() {
        return timeLimitMinute;
    }

    /**
     * Permette di inizializzare {@code timeLimitMilliseconds}
     * con il valore contenuto nel parametro {@code time}.
     * @param time tempo limite in millisecondi passato in input.
     */
    public void setTimeLimitMillis(final int time) {
        timeLimitMillisSeconds = time;
    }

    /**
     * Imposta il tempo iniziale di gioco in
     * millisecondi necessario per i controlli
     * sul tempo disponibile durante la partita.
     * @param time contiene il tempo d'inizio in millisecondi.
     */
    public void setStartTimeMill(final Long time) {
        startTimeMillis = time;
    }

    /**
     * Restituisce il tempo d'inizio del gioco
     * in millisecondi.
     * @return {@code startTimeMill} tempo d'inizio del gioco.
     */
    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    /**
     * Restituisce il numero di minuti a disposizione
     * per giocare in millisecondi.
     * @return {@code timeLimitMilleseconds} tempo limite in millisecondi.
     */
    public long getTimeLimitMill() {
        return timeLimitMillisSeconds;
    }

    /**
     * Restituisce il tempo calcolato nell'istante in cui
     * il metodo viene chiamato in millisecondi. È necessario
     * per effettuare controlli sul tempo ancora a
     * disposizione per giocare.
     * @return {@code currentTimeMill} tempo corrente in millisecondi.
     */
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Restituisce un oggetto Time clonato.
     */
    @Override
    public final Time clone() {
        Time clone = null;
        try {
            clone = (Time) super.clone();
        } catch (CloneNotSupportedException e) { }
        return clone;
    }
}
