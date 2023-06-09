package it.uniba.app.new_design.battleship.controller;


import java.io.IOException;

import it.uniba.app.battleship.exception.DifficultyNotSetException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.new_design.battleship.entity.Game;
import it.uniba.app.new_design.battleship.entity.Grid;
import it.uniba.app.utility.Input;

/**
 * {@code CommandHandler} è una classe che
 * gestisce i comandi con cui l'utente interagisce
 * con il gioco.
 */
public final class CommandHandler {

    private CommandHandler() { }

    /** Esegue un comando letto dal terminale.
     * Alcuni dei comandi attualmente interpretati sono:
     * <ul>
     * <li><{@code /help} : mostra l'elenco dei comandi disponibili</li>
     * <li><{@code /mostranavi} : mostra l'elenco delle navi disponibili</li>
     * <li><{@code /gioca} : avvia una nuova partita</li>
     * <li><{@code /mostralivello} : mostra il livello di difficoltà impostato</li>
     * <li><{@code /facile} : imposta il livello di difficoltà a 'facile'</li>
     * <li><{@code /medio} : imposta il livello di difficoltà a 'medio'</li>
     * <li><{@code /difficile} : imposta il livello di difficoltà a 'difficile'</li>
     * <li><{@code /svelagriglia} : mostra la griglia di gioco con le navi posizionate</li>
     * <li><{@code /esci} : permette di uscire dal gioco restituendo il controllo al SO</li>
     * </ul>
     * @param game
     */
    public void execute(final Game game) {
        try {
            String command = Input.get().toLowerCase();
            switch (command) {
                case "/help"            -> handleHelp();
                case "/mostranavi"      -> handleShowShip();
                case "/gioca"           -> handlePlay(game);
                case "/mostralivello"   -> handleShowDifficulty(game);
                case "/facile"          -> handleEasyDifficulty(game);
                case "/medio"           -> handleMediumDifficulty(game);
                case "/difficile"       -> handleHardDifficulty(game);
                case "/svelagriglia"    -> handleShowGameGrid(game);
                case "/esci"            -> handleExit();
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

    private void handlePlay(final Game game) {
        try {
            GameController.setEasyDifficulty(game);
            GameController.startSession(game);
        } catch (DifficultyNotSetException e) {
            System.out.println(e.getMessage());
        } catch (SessionAlreadyStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleShowDifficulty(final Game game) {
        try {
            Difficulty diff = GameController.getDifficulty(game);
            System.out.println(
                "livello impostato:\n"
                + "Nome " + diff.getLevel()
                + "Tentativi fallibili: " + diff.getMaxFailedAttempts()
                );
        } catch (CloneNotSupportedException e) {
            System.out.println("Impossibile recuperare l'informazione richiesta");
        }
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

    private void handleShowGameGrid(final Game game) {
        try {
            Grid grid = GameController.getSessionGrid(game);
            String message = GridController.genShipMap(grid);
            System.out.println(message);
        } catch (SessionNotStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleExit() {
        try {
            System.out.println("Conferma l'uscita dal gioco (si/no)");
            String confirm = Input.get().toLowerCase();
            switch (confirm) {
                case "si":
                    ExitController.requestExit();
                    break;
                case "no":
                    System.out.println("Operazione annullata");
                    break;
                default:
                    System.out.println("Comando non riconosciuto, operazione annullata");
                    break;
            }
        } catch (IOException err) { }
    }

}
