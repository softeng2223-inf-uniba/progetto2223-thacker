package it.uniba.app.battleship.controller;

import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;

public final class TimeController {

    private static final int SECOND = 60;
    private static final int MILLISECONDS = 1000;

    private TimeController() { };

    public static void setTime(final Time time) {
        time.setTimeLimitMill(time.getTimeLimitMin() * SECOND * MILLISECONDS);
        time.setStartTimeMill(System.currentTimeMillis());
    }

    public static void setTimeLimit(final Game game, final long timeSet)
    throws SessionAlreadyStartedException {
        if (game.isSessionStarted()) {
            throw new SessionAlreadyStartedException();
        }
        game.getTime().setTimeLimitMin(timeSet);
    }
}
