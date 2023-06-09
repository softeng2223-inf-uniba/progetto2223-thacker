package it.uniba.app.new_design.battleship.controller;


import java.io.IOException;

import it.uniba.app.game.exceptions.DifficultyNotSetException;
import it.uniba.app.game.exceptions.SessionAlreadyStartedException;
import it.uniba.app.new_design.battleship.entity.Game;
import it.uniba.app.utility.Input;

public final class CommandHandler {

    private CommandHandler() { }

    public void execute(Game game) {
        try {
            String command = Input.get().toLowerCase();
            switch (command) {
                case "/help"            -> handleHelp();
                case "/mostranavi"      -> handleShowShip();
                default                 -> System.err.println("[CH] Comando inesistente.");
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la lettura del comando: " + e.getMessage());
        }
    }

    private void handleShowShip() {
        ShowShipsController.showShips();
    }

    private void handleHelp() {
        HelpController.showHelp();
    }

    private void handlePlay(Game game) {
        try {
            GameController.setEasyDifficulty(game);
            GameController.startSession(game);
        } catch (DifficultyNotSetException e ) {
            System.out.println(e.getMessage());
        } catch (SessionAlreadyStartedException e) {
            System.out.println(e.getMessage());
        }
    }

}
