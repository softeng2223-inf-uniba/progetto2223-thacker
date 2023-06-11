package it.uniba.app.commandline.controller;


import java.io.IOException;

import it.uniba.app.battleship.exception.GameException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.controller.ExitController;
import it.uniba.app.battleship.controller.GameController;
import it.uniba.app.battleship.controller.GridController;
import it.uniba.app.battleship.controller.HelpController;
import it.uniba.app.battleship.controller.ShowShipsController;
import it.uniba.app.battleship.controller.TimeController;
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;
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
     * <li><{@code /standard} : imposta la dimensione della griglia a 10x10</li>
     * <li><{@code /large} : imposta la dimensione della griglia a 18x18</li>
     * <li><{@code /extralarge} : imposta la dimensione della griglia a 26x26</li>
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
    public static void execute(final Game game) {
        try {
            String command = Input.get().toLowerCase();
            switch (command) {
                case "/help"            -> handleHelp();
                case "/mostranavi"      -> handleShowShip();
                case "/mostragriglia"   -> handleShowHitMap(game);
                case "/standard"        -> handleStandardGrid(game);
                case "/large"           -> handleLargeGrid(game);
                case "/extralarge"      -> handleExtraLargeGrid(game);
                case "/gioca"           -> handlePlay(game);
                case "/mostralivello"   -> handleShowDifficulty(game);
                case "/facile"          -> handleEasyDifficulty(game);
                case "/medio"           -> handleMediumDifficulty(game);
                case "/difficile"       -> handleHardDifficulty(game);
                case "/svelagriglia"    -> handleShowGameGrid(game);
                case "/mostratentativi" -> handleShowAttempts(game);
                case "/esci"            -> handleExit();
                default                 -> handleDefaultOrShoot(game, command);
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la lettura del comando: " + e.getMessage());
        }
    }

    private static boolean gameTimeCheck(final Game game) {
        if (TimeController.isTimeOver(game)) {
            game.getTime().setTimeLimitMin(0);
            try {
                GameController.endSession(game);
            } catch (SessionNotStartedException e) { }
            return true;
        }
            return false;
    }

    private static int handleTimeInput(final String input) {
        int time = 0;
        try {
            time = Integer.parseInt(input);
            if (time < 1) {
                System.out.println("Valore non valido");
                return 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("[CH] Comando inesistente.");
        }
        return time;
    }

    private static void handleDefaultOrShoot(final Game game, final String command) {
        String regex = "[a-z]-[0-9]{1,2}";
        if (command.matches(regex)) {
            try {
                GameController.strike(game, command);
                System.out.println(GridController.genHitMap(game.getSessionGrid()));
            } catch (GameException err) {
                System.out.println(err.getMessage());
            }
        } else {
            System.out.println("[CH] comando inesistente");
        }
    }

    private static void handleShowHitMap(final Game game) {
        try {
            Grid grid = GameController.getSessionGrid(game);
            String str = GridController.genHitMap(grid);
            System.out.println(str);
        } catch (SessionNotStartedException err) {
            System.out.println(err.getMessage());
        }
    }

    private static void handleStandardGrid(final Game game) {
        try {
            GridController.standardGridSize(game);
        } catch (SessionAlreadyStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleLargeGrid(final Game game) {
        try {
            GridController.largeGridSize(game);
        } catch (SessionAlreadyStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleExtraLargeGrid(final Game game) {
        try {
            GridController.extraLargeGridSize(game);
        } catch (SessionAlreadyStartedException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void handleShowShip() {
        ShowShipsController.showShips();
    }

    private static void handleHelp() {
        HelpController.showHelp();
    }

    private static void handlePlay(final Game game) {
        try {
            GameController.startSession(game);
            handleShowHitMap(game);
        } catch (SessionAlreadyStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleShowDifficulty(final Game game) {
        if (!game.isDifficultySet()) {
            setDefaultDifficulty(game);
        }

        try {
            Difficulty diff = GameController.getDifficulty(game);
            System.out.println(
                "Livello impostato:\n"
                + "Nome : " + diff.getLevel() + "\n"
                + "Numero massimo di tentativi fallibili : " + diff.getMaxFailedAttempts()
                );
        } catch (CloneNotSupportedException e) {
            System.out.println("Impossibile recuperare l'informazione richiesta");
        }
    }

    private static void handleEasyDifficulty(final Game game) {
        try {
            GameController.setEasyDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a facile.");
        } catch (SessionAlreadyStartedException err) {
            System.out.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }

    private static void handleMediumDifficulty(final Game game) {
        try {
            GameController.setMediumDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a medio.");
        } catch (SessionAlreadyStartedException err) {
            System.out.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }

    private static void handleHardDifficulty(final Game game) {
        try {
            GameController.setHardDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a difficile.");
        } catch (SessionAlreadyStartedException err) {
            System.out.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }

    private static void handleShowGameGrid(final Game game) {
        try {
            Grid grid = GameController.getSessionGrid(game);
            String message = GridController.genShipMap(grid);
            System.out.println(message);
        } catch (SessionNotStartedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleShowAttempts(final Game game) {
        try {
            String message =
                "tentativi effettuati: " + GameController.getAttempts(game) + "\n"
                + "di cui falliti: " + GameController.getFailedAttempts(game) + "\n"
                + "massimo fallibili: " + GameController.getDifficulty(game).getMaxFailedAttempts();
            System.out.println(message);
        } catch (SessionNotStartedException e) {
            System.out.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("Impossibile recuperare informazioni sul livello di difficoltà");
        }
    }

    private static void handleExit() {
        try {
            System.out.println("Conferma l'uscita dal gioco (si/no)");
            String confirm = Input.get().toLowerCase();
            switch (confirm) {
                case "si":
                    ExitController.getInstance().requestExit();
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

    private static void setDefaultDifficulty(final Game game) {
        try {
            GameController.setEasyDifficulty(game);     //difficoltà predefinita: Facile
        } catch (SessionAlreadyStartedException e) { }
    }

}
