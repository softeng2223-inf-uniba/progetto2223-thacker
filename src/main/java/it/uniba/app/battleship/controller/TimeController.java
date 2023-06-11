package it.uniba.app.battleship.controller;

public final class TimeController {

    private static final int SECOND = 60;
    private static final int MILLISECONDS = 1000;

    private TimeController() { };

    public static void setTime(final Time time) {
        time.setTimeLimitMill(time.getTimeLimitMin() * SECOND * MILLISECONDS);
        time.setStartTimeMill(System.currentTimeMillis());
    }
}
