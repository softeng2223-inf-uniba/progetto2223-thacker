package it.uniba.app.new_design.battleship.controller;

import it.uniba.app.game.exceptions.SessionAlreadyStartedException;

public class GameController {
    private static boolean isSessionStarted = false;

    public static void startSession() throws SessionAlreadyStartedException {
        if (isSessionStarted) {
            throw new SessionAlreadyStartedException();
        }

        isSessionStarted = true;
    }
}
