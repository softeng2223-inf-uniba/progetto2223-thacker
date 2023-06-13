package it.uniba.app.commandline;

// Import eccezioni.
import java.io.IOException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.exception.CellAlreadyMarkedException;
import it.uniba.app.battleship.exception.OutOfMapException;
import it.uniba.app.battleship.controller.GameController;
import it.uniba.app.battleship.controller.GridController;
import it.uniba.app.battleship.controller.DifficultyController;
import it.uniba.app.battleship.controller.TimeController;
// Import classi entity.
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;

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

    /* CONTROLLERS */
    private static ShowGridController CONTROL_SHOWGRID = ShowGridController.getInstance();

    /**
     * Esegue un comando con o senza parametri.
     * @param game istanza di {@link Game}
     */
    public void handleCommand(final Game game) {
        try {
            Output.printEnterCommand(game.isSessionStarted());
            String command = Input.get().toLowerCase();
            String[] tokens = command.split(" ");
            if (gameTimeCheck(game)) {
                Output.printTimeOut();
                return;
            }
            switch (tokens.length) {
                case 1  -> executeNoArgs(game, command);
                case 2  -> executeArgs(game, tokens[0], tokens[1]);
                default -> Output.printCommandNotRecognised(command);
            }
        } catch (IOException e) {
            Output.printCantReadInput();
        }
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
        } else if (!valueStr.matches("^[1-9]\\d*$")) {
            if (commandStr.equals("/tempo") && valueStr.equals("0")) {
                handleTime(game, 0);
            } else {
                Output.printNumberFormatError(valueStr);
            }
        } else {
            int value = Integer.parseInt(valueStr);
            switch (commandStr) {
                case "/tentativi" -> handleCustomDifficulty(game, value);
                case "/tempo" -> handleTime(game, value);
                case "/facile" -> handleCustomEasyDifficulty(game, value);
                case "/medio" -> handleCustomMediumDifficulty(game, value);
                case "/difficile" -> handleCustomHardDifficulty(game, value);
                default -> { }
            }
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
            GameController.getInstance().setCustomDifficulty(game, value);
        } catch (SessionAlreadyStartedException e) {
            Output.printCantSetDiffDuringSession();
        }
    }
    private void handleShowTime(final Game game) {
        Output.println(ShowTimeController.showTime(game));
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'facile' a num.
     * Inoltre imposta la difficoltà della sessione a 'facile'.
     * @param game sessione di gioco.
     * @param num numero di tentativi massimi fallibili.
     */
    private void handleCustomEasyDifficulty(final Game game, final int num) {
        DifficultyController.getInstance().setCustomEasy(num);
        handleEasyDifficulty(game, true);
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'medio' a num.
     * Inoltre imposta la difficoltà della sessione a 'medio'.
     * @param game sessione di gioco.
     * @param num numero di tentativi massimi fallibili.
     */
    private void handleCustomMediumDifficulty(final Game game, final int num) {
        DifficultyController.getInstance().setCustomMedium(num);
        handleMediumDifficulty(game, true);
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'difficile' a num.
     * Inoltre imposta la difficoltà della sessione a 'difficile'.
     * @param game sessione di gioco
     * @param num numero di tentativi massimi fallibili.
     */
    private void handleCustomHardDifficulty(final Game game, final int num) {
        DifficultyController.getInstance().setCustomHard(num);
        handleHardDifficulty(game, true);
    }
    private boolean gameTimeCheck(final Game game) {
        if (TimeController.getInstance().isTimeOver(game)) {
            try {
                GameController.getInstance().endSession(game);
                GameController.getInstance().setTime(game, 0);
            } catch (SessionNotStartedException | SessionAlreadyStartedException e) {

            }

            return true;
        }
        return false;
    }
    private void handleTime(final Game game, final int value) {
        try {
            GameController.getInstance().setTime(game, value);
            Output.printSetTime(value);
        } catch (SessionAlreadyStartedException e) {
            Output.printCantSetTime();
        }
    }
    private void handleDefaultOrShoot(final Game game, final String command) {
        String regex = "[a-z]-[0-9]{1,2}";
        if (command.matches(regex)) {
            try {
                StrikeController.strike(game, command);
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
            GridController.getInstance().standardGridSize(game);
            Output.printSetGridSize(Grid.getSize());
        } catch (SessionAlreadyStartedException e) {
            Output.printCantChangeGridSize();
        }
    }
    private void handleLargeGrid(final Game game) {
        try {
            GridController.getInstance().largeGridSize(game);
            Output.printSetGridSize(Grid.getSize());
        } catch (SessionAlreadyStartedException e) {
            Output.printCantChangeGridSize();
        }
    }
    private void handleExtraLargeGrid(final Game game) {
        try {
            GridController.getInstance().extraLargeGridSize(game);
            Output.printSetGridSize(Grid.getSize());
        } catch (SessionAlreadyStartedException e) {
            Output.printCantChangeGridSize();
        }
    }
    private void handleShowShip() {
        Output.clearScreen();
        Output.println(ShowShipsController.getShipInfo());
    }

    private void handleHelp() {
        Output.clearScreen();
        HelpController.getInstance().showHelp();
    }

    private void handlePlay(final Game game) {
        try {
            GameController.getInstance().startSession(game);
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
        try {
            Difficulty diff = GameController.getInstance().getDifficulty(game);
            Output.printGameLevel(diff.getNameLevel(), diff.getMaxFailedAttempts());
        } catch (CloneNotSupportedException e) {
            Output.printCantClone();
        }
    }
    private void handleEasyDifficulty(final Game game, final boolean custom) {
        try {
            GameController.getInstance().setEasyDifficulty(game);
            Output.printSetDifficulty(game.getDifficulty().getNameLevel(),
                                      game.getDifficulty().getMaxFailedAttempts());
        } catch (SessionAlreadyStartedException err) {
            if (!custom) {
                Output.printCantSetDiffDuringSession();
            } else {
                Output.printNotSetCustomDiff();
            }
        } catch (CloneNotSupportedException e) {
            Output.printCantClone();
        }
    }
    private void handleMediumDifficulty(final Game game, final boolean custom) {
        try {
            GameController.getInstance().setMediumDifficulty(game);
            Output.printSetDifficulty(game.getDifficulty().getNameLevel(),
                                      game.getDifficulty().getMaxFailedAttempts());
        } catch (SessionAlreadyStartedException err) {
            if (!custom) {
                Output.printCantSetDiffDuringSession();
            } else {
                Output.printNotSetCustomDiff();
            }
        } catch (CloneNotSupportedException e) {
            Output.printCantClone();
        }
    }
    private void handleHardDifficulty(final Game game, final boolean custom) {
        try {
            GameController.getInstance().setHardDifficulty(game);
            Output.printSetDifficulty(game.getDifficulty().getNameLevel(),
                                      game.getDifficulty().getMaxFailedAttempts());
        } catch (SessionAlreadyStartedException e) {
            if (!custom) {
                Output.printCantSetDiffDuringSession();
            } else {
                Output.printNotSetCustomDiff();
            }
        } catch (CloneNotSupportedException e) {
            Output.printCantClone();
        }
    }
    private void handleShowGameGrid(final Game game) {
        try {
            Grid grid = GameController.getInstance().getSessionGrid(game);
            Output.clearScreen();
            Output.printShipMap(CONTROL_SHOWGRID.genShipMap(grid));
        } catch (SessionNotStartedException e) {
            Output.printShowGridSessionNotStarted();
        }
    }
    private void handleShowAttempts(final Game game) {
        try {
            GameController gController = GameController.getInstance();
            Output.printShowAttempts(gController.getAttempts(game),
                                     gController.getFailedAttempts(game),
                                     gController.getDifficulty(game).getMaxFailedAttempts());
        } catch (SessionNotStartedException e) {
            Output.printShowAttemptsSessionNotStarted();
        } catch (CloneNotSupportedException e) {
            Output.printCantClone();
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
                    GameController.getInstance().endSession(game);
                    try {
                        GameController.getInstance().setTime(game, 0);
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
                case "si" -> ExitController.getInstance().requestExit();
                case "no" -> Output.printNotConfirm();
                default   -> Output.printConfirmCommandNotFound();
            }
        } catch (IOException err) {
            Output.printCantReadInput();
        }
    }
    private void setDefaultDifficulty(final Game game) {
        try {
            GameController.getInstance().setEasyDifficulty(game);     //difficoltà predefinita: Facile
        } catch (SessionAlreadyStartedException e) {
            Output.print(e.getMessage());
        }
    }
}
