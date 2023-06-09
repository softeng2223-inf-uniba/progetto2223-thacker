package it.uniba.app.new_design.battleship.controller;


import java.io.IOException;

import it.uniba.app.new_design.battleship.entity.Game;
import it.uniba.app.utility.Input;
import it.uniba.app.game.exceptions.SessionAlreadyStartedException;

public final class CommandHandler {

    private CommandHandler() { }

    public void execute(Game game) {
        try {
            String command = Input.get().toLowerCase();
            switch (command) {
                case "/help"            -> handleHelp();
                case "/mostranavi"      -> handleShowShip();
                case "/facile"          -> handleEasyDifficulty(game);
                case "/medio"           -> handleMediumDifficulty(game);
                case "/difficile"       -> handleHardDifficulty(game);
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

    private void handleEasyDifficulty(final Game game) {
        try {
            GameController.setEasyDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a facile.");
        } catch (SessionAlreadyStartedException err) {
            System.out.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }

    private void handleMediumDifficulty(final Game game) {
        try {
            GameController.setMediumDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a medio.");
        } catch (SessionAlreadyStartedException err) {
            System.out.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }

    private void handleHardDifficulty(final Game game) {
        try {
            GameController.setHardDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a difficile.");
        } catch (SessionAlreadyStartedException err) {
            System.out.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }

}
