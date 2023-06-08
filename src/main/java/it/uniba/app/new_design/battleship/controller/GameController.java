package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.game.exceptions.SessionAlreadyStartedException;
import it.uniba.app.game.exceptions.SessionNotStartedException;

public class GameController {
    private static boolean isSessionStarted = false;

    private GameController() { }

    public static void startSession() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        isSessionStarted = true;
    }

    public static void endSession() throws SessionNotStartedException {
        if (!isSessionStarted) {
            throw new SessionNotStartedException();
        }
        isSessionStarted = false;
    }
}
