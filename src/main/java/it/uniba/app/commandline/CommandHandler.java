package it.uniba.app.commandline;

// Import eccezioni.
import java.io.IOException;

import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.InvalidValueException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.GameController;
// Import classi entity.
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;
import it.uniba.app.battleship.entity.Ship;

// Altro.
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * {@code <<Control>>}<hr>
 * {@code CommandHandler} è una classe che
 * gestisce i comandi con cui l'utente interagisce
 * con il gioco.
 */
public final class CommandHandler {
    /* COMMANDLINE CONTROLLERS */
    private static final ShowGridController CONTROL_SHOWGRID = ShowGridController.getInstance();
    private static final ShowTimeController CONTROL_SHOWTIME = ShowTimeController.getInstance();
    private static final HelpController CONTROL_HELP = HelpController.getInstance();
    private static final ShowShipsController CONTROL_SHOWSHIPS = ShowShipsController.getInstance();
    private static final ExitController CONTROL_EXIT = ExitController.getInstance();

    /* BATTLESHIP CONTROLLERS */
    private static final GameController CONTROL_GAME = GameController.getInstance();
    private static final StrikeController CONTROL_STRIKE = StrikeController.getInstance();

    private static class Holder {
        private static final CommandHandler INSTANCE = new CommandHandler();
    }

    private CommandHandler() { };

    /**
     * Fornisce l'istanza del gestore dei comandi.
     * @return istanza di CommandHandler
     */
    public static CommandHandler getInstance() {
        return Holder.INSTANCE;
    }

    private static final Set<String> COMMANDS_WITH_PARAMS = new LinkedHashSet<>() {{
        add("/tentativi");
        add("/tempo");
        add("/facile");
        add("/medio");
        add("/difficile");
    }};

    /**
     * Esegue un comando con o senza parametri.
     * @param game istanza di {@link Game}
     */
    public void handleCommand(final Game game) {
        try {
            if (game.isSessionStarted() && isEnd(game)) {
                GameController.getInstance().reset(game);
                return;
            }
            Output.printEnterCommand(game.isSessionStarted());
            String command = Input.get().toLowerCase();
            String[] tokens = command.split(" ");
            switch (tokens.length) {
                case 1  -> executeNoArgs(game, command);
                case 2  -> executeArgs(game, tokens[0], tokens[1]);
                default -> Output.printCommandNotRecognised(command);
            }
        } catch (IOException e) {
            Output.printCantReadInput();
        }
    }
    private boolean isEnd(final Game game) {
        // Se il tempo è scaduto, termina la partita.
        if (gameTimeCheck(game)) {
            Output.printTimeOut();
            GameController.getInstance().reset(game);
            return true;
        }
        // Se hai esausto i tentativi, termina la partita.
        if (CONTROL_GAME.getFailedAttempts(game)
            == game.getDifficulty().getMaxFailedAttempts()) {
                Output.printEndGameAttempts(CONTROL_SHOWGRID.genShipMap(game.getSessionGrid()));
                game.endSession();
                return true;
            }
        // Se tutte le navi sono state affondate, termina la partita.
        if (game.getSunkShips() == Ship.getNumberInstanceShips()) {
            Output.printWinGame(CONTROL_GAME.getAttempts(game));
            game.endSession();
            return true;
        }
        return false;
    }
    /**
     * Esegue un comando con parametri.
     * @param game istanza di {@link Game}
     * @param commandStr stringa che rappresenta il comando.
     * @param valueStr stringa che rappresenta il parametro (intero >0).
     */
    private void executeArgs(final Game game, final String commandStr, final String valueStr) {
        if (!COMMANDS_WITH_PARAMS.contains(commandStr)) {
            Output.printCommandWithParamsNotRecognised(commandStr);
            return;
        }
        try {
            int value = Integer.parseInt(valueStr);
            switch (commandStr) {
                case "/tentativi" -> handleCustomDifficulty(game, value);
                case "/tempo" -> handleTime(game, value);
                case "/facile" -> handleCustomEasyDifficulty(game, value);
                case "/medio" -> handleCustomMediumDifficulty(game, value);
                case "/difficile" -> handleCustomHardDifficulty(game, value);
                default -> { }
            }
        } catch (NumberFormatException e) {
            // Integer.parseInt() exception
            Output.printCommandWithParamsNotNumber(commandStr);
        } catch (InvalidValueException e) {
            // Command exception
            Output.printCommandWithParamsNumberNotPositive(commandStr);
        }
    }
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
     * <li><{@code /mostratempo} : mostra il tempo trascorso e i minuti ancora disponibili per giocare</li>
     * <li><{@code /esci} : permette di uscire dal gioco restituendo il controllo al SO</li>
     * </ul>
     * @param game
     */
    private void executeNoArgs(final Game game, final String command) {
        switch (command) {
            case "/help"            -> handleHelp();
            case "/mostranavi"      -> handleShowShip();
            case "/mostragriglia"   -> handleShowHitMap(game);
            case "/standard"        -> handleStandardGrid(game);
            case "/large"           -> handleLargeGrid(game);
            case "/extralarge"      -> handleExtraLargeGrid(game);
            case "/gioca"           -> handlePlay(game);
            case "/abbandona"       -> handleEndSession(game);
            case "/mostralivello"   -> handleShowDifficulty(game);
            case "/facile"          -> handleEasyDifficulty(game, false);
            case "/medio"           -> handleMediumDifficulty(game, false);
            case "/difficile"       -> handleHardDifficulty(game, false);
            case "/svelagriglia"    -> handleShowGameGrid(game);
            case "/mostratentativi" -> handleShowAttempts(game);
            case "/mostratempo"     -> handleShowTime(game);
            case "/esci"            -> handleExitGame();
            default -> handleDefaultOrShoot(game, command);
        }
    }
    private void handleCustomDifficulty(final Game game, final int value) {
        try {
            CONTROL_GAME.setCustomDifficulty(game, value);
        } catch (SessionAlreadyStartedException e) {
            Output.printCantSetDiffDuringSession();
        } catch (InvalidValueException e) {
            Output.print(e.getMessage());
        }
    }
    private void handleShowTime(final Game game) {
        Output.println(CONTROL_SHOWTIME.showTime(game));
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'facile' a num.
     * Inoltre imposta la difficoltà della sessione a 'facile'.
     * @param game sessione di gioco.
     * @param num numero di tentativi massimi fallibili.
     */
    private void handleCustomEasyDifficulty(final Game game, final int num) {
        CONTROL_GAME.setCustomEasyDifficulty(game, num);
        handleEasyDifficulty(game, true);
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'medio' a num.
     * Inoltre imposta la difficoltà della sessione a 'medio'.
     * @param game sessione di gioco.
     * @param num numero di tentativi massimi fallibili.
     */
    private void handleCustomMediumDifficulty(final Game game, final int num) {
        CONTROL_GAME.setCustomMediumDifficulty(game, num);
        handleMediumDifficulty(game, true);
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'difficile' a num.
     * Inoltre imposta la difficoltà della sessione a 'difficile'.
     * @param game sessione di gioco
     * @param num numero di tentativi massimi fallibili.
     */
    private void handleCustomHardDifficulty(final Game game, final int num) {
        CONTROL_GAME.setCustomHardDifficulty(game, num);
        handleHardDifficulty(game, true);
    }

    private boolean gameTimeCheck(final Game game) {
        if (CONTROL_GAME.isTimeOver(game)) {
            try {
                CONTROL_GAME.endSession(game);
                CONTROL_GAME.setGameTimeMinute(game, 0);
            } catch (SessionNotStartedException e) {
                Output.print(e.getMessage());
            } catch (SessionAlreadyStartedException e) {
                Output.print(e.getMessage());
            }
            return true;
        }
        return false;
    }

    private void handleTime(final Game game, final int value) {
        try {
            CONTROL_GAME.setGameTimeMinute(game, value);
            Output.printSetTime(value);
        } catch (SessionAlreadyStartedException e) {
            Output.printCantSetTime();
        }
    }

    private void handleDefaultOrShoot(final Game game, final String command) {
        String regex = "[a-z]-[0-9]{1,2}";
        if (command.matches(regex)) {
            try {
                CONTROL_STRIKE.strike(game, command);
                Output.printHitMap(CONTROL_SHOWGRID.genHitMap(game));
            } catch (SessionNotStartedException err) {
                Output.printHitSessionNotStarted();
            } catch (CellAlreadyMarkedException err) {
                Output.printHitCellAlreadyHit(command);
            } catch (OutOfMapException err) {
                Output.printHitOutOfMap(command);
            }
        } else {
            Output.printCommandWithoutParamsNotRecognised(command);
        }
    }

    private void handleShowHitMap(final Game game) {
        try {
            String map = CONTROL_SHOWGRID.genHitMap(game);
            Output.clearScreen();
            Output.printHitMap(map);
        } catch (SessionNotStartedException err) {
            Output.printCantShowHitMap();
        }
    }

    private void handleStandardGrid(final Game game) {
        try {
            CONTROL_GAME.standardGridSize(game);
            Output.printSetGridSize(Grid.getSize());
        } catch (SessionAlreadyStartedException e) {
            Output.printCantChangeGridSize();
        }
    }

    private void handleLargeGrid(final Game game) {
        try {
            CONTROL_GAME.largeGridSize(game);
            Output.printSetGridSize(Grid.getSize());
        } catch (SessionAlreadyStartedException e) {
            Output.printCantChangeGridSize();
        }
    }

    private void handleExtraLargeGrid(final Game game) {
        try {
            CONTROL_GAME.extraLargeGridSize(game);
            Output.printSetGridSize(Grid.getSize());
        } catch (SessionAlreadyStartedException e) {
            Output.printCantChangeGridSize();
        }
    }

    private void handleShowShip() {
        Output.clearScreen();
        Output.println(CONTROL_SHOWSHIPS.getShipInfo());
    }

    private void handleHelp() {
        Output.clearScreen();
        CONTROL_HELP.showHelp();
    }

    private void handlePlay(final Game game) {
        try {
            CONTROL_GAME.startSession(game);
            Output.clearScreen();
            handleShowHitMap(game);
        } catch (SessionAlreadyStartedException e) {
            Output.printStartSessionAlreadyStarted();
        }
    }

    private void handleShowDifficulty(final Game game) {
        if (!game.isDifficultySet()) {
            setDefaultDifficulty(game);
        }
        Difficulty diff = CONTROL_GAME.getDifficulty(game);
        Output.printGameLevel(diff.getNameLevel(), diff.getMaxFailedAttempts());
    }

    private void handleEasyDifficulty(final Game game, final boolean custom) {
        try {
            CONTROL_GAME.setEasyDifficulty(game);
            Output.printSetDifficulty(game.getDifficulty().getNameLevel(),
                    game.getDifficulty().getMaxFailedAttempts());
        } catch (SessionAlreadyStartedException err) {
            if (!custom) {
                Output.printCantSetDiffDuringSession();
            } else {
                Output.printNotSetCustomDiff();
            }
        }
    }

    private void handleMediumDifficulty(final Game game, final boolean custom) {
        try {
            CONTROL_GAME.setMediumDifficulty(game);
            Output.printSetDifficulty(game.getDifficulty().getNameLevel(),
                    game.getDifficulty().getMaxFailedAttempts());
        } catch (SessionAlreadyStartedException err) {
            if (!custom) {
                Output.printCantSetDiffDuringSession();
            } else {
                Output.printNotSetCustomDiff();
            }
        }
    }

    private void handleHardDifficulty(final Game game, final boolean custom) {
        try {
            CONTROL_GAME.setHardDifficulty(game);
            Output.printSetDifficulty(game.getDifficulty().getNameLevel(),
                    game.getDifficulty().getMaxFailedAttempts());
        } catch (SessionAlreadyStartedException e) {
            if (!custom) {
                Output.printCantSetDiffDuringSession();
            } else {
                Output.printNotSetCustomDiff();
            }
        }
    }

    private void handleShowGameGrid(final Game game) {
        try {
            Grid grid = CONTROL_GAME.getSessionGrid(game);
            Output.clearScreen();
            Output.print("[VISTA AVVERSARIO]\n]");
            Output.printShipMap(CONTROL_SHOWGRID.genShipMap(grid));
        } catch (SessionNotStartedException e) {
            Output.printShowGridSessionNotStarted();
        }
    }

    private void handleShowAttempts(final Game game) {
        try {
            GameController gController = CONTROL_GAME;
            Output.printShowAttempts(gController.getAttempts(game),
                                     gController.getFailedAttempts(game),
                                     gController.getDifficulty(game).getMaxFailedAttempts());
        } catch (SessionNotStartedException e) {
            Output.printShowAttemptsSessionNotStarted();
        }
    }

    private void handleEndSession(final Game game) {
        try {
            if (!game.isSessionStarted()) {
                throw new SessionNotStartedException();
            }
            Output.printConfirmOperation("abbandonare la partita");
            String confirm = Input.get().toLowerCase();
            switch (confirm) {
                case "si" -> {
                    Output.printEndSessionConfirm(CONTROL_SHOWGRID.genShipMap(game.getSessionGrid()));
                    CONTROL_GAME.endSession(game);
                    try {
                        GameController.getInstance().reset(game);
                    } catch (SessionAlreadyStartedException ignored) { }
                }
                case "no" -> Output.printNotConfirm();
                default   -> Output.printConfirmCommandNotFound();
            }
        } catch (SessionNotStartedException e) {
            Output.printCantExit();
        } catch (IOException e) {
            Output.printCantReadInput();
        }
    }

    private void handleExitGame() {
        try {
            Output.printConfirmOperation("uscire dal gioco");
            String confirm = Input.get().toLowerCase();
            switch (confirm) {
                case "si" -> CONTROL_EXIT.requestExit();
                case "no" -> Output.printNotConfirm();
                default   -> Output.printConfirmCommandNotFound();
            }
        } catch (IOException err) {
            Output.printCantReadInput();
        }
    }

    private void setDefaultDifficulty(final Game game) {
        try {
            CONTROL_GAME.setEasyDifficulty(game);     //difficoltà predefinita: Facile
        } catch (SessionAlreadyStartedException e) {
            Output.print(e.getMessage());
        }
    }
}
