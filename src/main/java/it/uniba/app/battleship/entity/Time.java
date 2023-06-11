package it.uniba.app.battleship.entity;

public class Time {

    private static long timeLimitMinute;
    private static long startTimeMill;
    private static long timeLimitMilliseconds;
    private static long currentTimeMill;

    public Time() { };

    public void setTimeLimitMin(final long time) {
        timeLimitMinute = time;
    }

    public void setTimeLimitMill(final Long time) {
        timeLimitMilliseconds = time;
    }
}
