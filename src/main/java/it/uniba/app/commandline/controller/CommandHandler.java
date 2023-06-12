package it.uniba.app.commandline.controller;

// Import eccezioni.
import java.io.IOException;
import it.uniba.app.battleship.exception.SessionAlreadyStartedException;
import it.uniba.app.battleship.exception.SessionNotStartedException;
import it.uniba.app.battleship.exception.GameException;
// Import classi controller.
import it.uniba.app.battleship.controller.ExitController;
import it.uniba.app.battleship.controller.GameController;
import it.uniba.app.battleship.controller.GridController;
import it.uniba.app.battleship.controller.HelpController;
import it.uniba.app.battleship.controller.DifficultyController;
import it.uniba.app.battleship.controller.ShowShipsController;
import it.uniba.app.battleship.controller.TimeController;
// Import classi entity.
import it.uniba.app.battleship.entity.Difficulty;
import it.uniba.app.battleship.entity.Game;
import it.uniba.app.battleship.entity.Grid;
// Import classi boundary.
import it.uniba.app.utility.Input;
// Altro.
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * {@code CommandHandler} è una classe che
 * gestisce i comandi con cui l'utente interagisce
 * con il gioco.
 */
public final class CommandHandler {
    private CommandHandler() { }
    /**
     * Esegue un comando passato come parametro.
     * @param game istanza di {@link Game}
     */
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
    public static void handleCommand(final Game game) {
        try {
            String command = Input.get().toLowerCase();
            String[] tokens = command.split(" ");
            if (gameTimeCheck(game)) {
                System.err.println("Tempo scaduto. Comando ignorato. Hai perso.");
                return;
            }
            switch (tokens.length) {
                case 1 -> executeNoArgs(game, command);
                case 2 -> executeArgs(game, tokens[0], tokens[1]);
                default -> System.err.println("[CH] '" + command + "' non è un comando valido."
                        + "\nUsa il comando '/help' per vedere la lista dei comandi disponibili.");
            }
        } catch (IOException e) {
            System.err.println("Si è verificato un errore durante la lettura del comando: " + e.getMessage());
        }
    }
    /**
     * Esegue un comando con parametri.
     * @param game istanza di {@link Game}
     * @param commandStr stringa che rappresenta il comando.
     * @param valueStr stringa che rappresenta il parametro (intero >0).
     */
    private static void executeArgs(final Game game, final String commandStr, final String valueStr) {
        if (!COMMANDS_WITH_PARAMS.contains(commandStr)) {
            System.err.println("[CH] '" + commandStr + "' non e' un comando con parametro valido."
                    + "\nUsa il comando '/help' per vedere la lista dei comandi disponibili.");
        } else if (!valueStr.matches("^[1-9]\\d*$")) {
            System.err.println("[CH] '" + valueStr + "' non e' numero un intero >0.");
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
    private static void executeNoArgs(final Game game, final String command) {
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
            case "/facile"          -> handleEasyDifficulty(game);
            case "/medio"           -> handleMediumDifficulty(game);
            case "/difficile"       -> handleHardDifficulty(game);
            case "/svelagriglia"    -> handleShowGameGrid(game);
            case "/mostratentativi" -> handleShowAttempts(game);
            case "/mostratempo"     -> handleShowTime(game);
            case "/esci"            -> handleExit();
            default -> handleDefaultOrShoot(game, command);
        }
    }

    private static void handleCustomDifficulty(final Game game, final int value) {
        try {
            GameController.setCustomDifficulty(game, value);
        } catch (SessionAlreadyStartedException e) {
            System.err.println("[CH] Non puoi cambiare difficoltà se la partita è già iniziata.");
        }
    }
    private static void handleShowTime(final Game game) {
        String time = TimeController.showTime(game);
        System.out.println(time);
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'facile' a num.
     * Inoltre imposta la difficoltà della sessione a 'facile'.
     * @param game sessione di gioco.
     * @param num numero di tentativi massimi fallibili.
     */
    private static void handleCustomEasyDifficulty(final Game game, final int num) {
        DifficultyController.setCustomEasy(num);
        try {
            GameController.setEasyDifficulty(game);
            System.out.println("Ok");
        } catch (SessionAlreadyStartedException e) {
            System.err.println("[CH] Non puoi cambiare difficoltà se la partita è già iniziata.");
        }
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'medio' a num.
     * Inoltre imposta la difficoltà della sessione a 'medio'.
     * @param game sessione di gioco.
     * @param num numero di tentativi massimi fallibili.
     */
    private static void handleCustomMediumDifficulty(final Game game, final int num) {
        DifficultyController.setCustomMedium(num);
        try {
            GameController.setMediumDifficulty(game);
            System.out.println("Ok");
        } catch (SessionAlreadyStartedException e) {
            System.err.println("[CH] Non puoi cambiare difficoltà se la partita è già iniziata.");
        }
    }
    /**
     * Imposta i tentativi massimi fallibili per la difficoltà 'difficile' a num.
     * Inoltre imposta la difficoltà della sessione a 'difficile'.
     * @param game sessione di gioco
     * @param num numero di tentativi massimi fallibili.
     */
    private static void handleCustomHardDifficulty(final Game game, final int num) {
        DifficultyController.setCustomHard(num);
        try {
            GameController.setHardDifficulty(game);
            System.out.println("Ok");
        } catch (SessionAlreadyStartedException e) {
            System.err.println("[CH] Non puoi cambiare difficoltà se la partita è già iniziata.");
        }
    }

    private static boolean gameTimeCheck(final Game game) {
        if (TimeController.isTimeOver(game)) {
            try {
                GameController.endSession(game);
                GameController.setTime(game, 0);
            } catch (SessionNotStartedException | SessionAlreadyStartedException e) {

            }

            return true;
        }
            return false;
    }

    private static void handleTime(final Game game, final int value) {
            try {
                GameController.setTime(game, value);
                System.out.println("OK, il numero di minuti a disposizione per giocare e': " + value);
            } catch (SessionAlreadyStartedException e) {
                System.err.println(e.getMessage());
            }
    }

    private static void handleDefaultOrShoot(final Game game, final String command) {
        String regex = "[a-z]-[0-9]{1,2}";
        if (command.matches(regex)) {
            try {
                GameController.strike(game, command);
                System.out.println(GridController.genHitMap(game.getSessionGrid()));
            } catch (GameException err) {
                System.err.println("[CH] Non puoi lanciare un colpo se non inizi una partita. "
                        + "\nUtilizza il comando '/gioca' per iniziare una partita.");
            }
        } else {
            System.err.println("[CH] '" + command + "' non è un comando senza parametri valido."
                    + "\nUsa il comando '/help' per vedere la lista dei comandi disponibili.");
        }
    }
    private static void handleShowHitMap(final Game game) {
        try {
            Grid grid = GameController.getSessionGrid(game);
            String str = GridController.genHitMap(grid);
            System.out.println(str);
        } catch (SessionNotStartedException err) {
            System.err.println(err.getMessage());
        }
    }
    private static void handleStandardGrid(final Game game) {
        try {
            GridController.standardGridSize(game);
        } catch (SessionAlreadyStartedException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void handleLargeGrid(final Game game) {
        try {
            GridController.largeGridSize(game);
        } catch (SessionAlreadyStartedException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void handleExtraLargeGrid(final Game game) {
        try {
            GridController.extraLargeGridSize(game);
        } catch (SessionAlreadyStartedException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void handleShowShip() {
        System.out.println(ShowShipsController.getShipInfo());
    }

    private static void handleHelp() {
        HelpController.showHelp();
    }

    private static void handlePlay(final Game game) {
        try {
            GameController.startSession(game);
            handleShowHitMap(game);
        } catch (SessionAlreadyStartedException e) {
            System.err.println("[CH] Non puoi iniziare una nuova partita se una è già in corso. "
                    + "\nSe vuoi terminare la partita corrente usa il comando '/abbandona'");
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
                + "Nome : " + diff.getNameLevel() + "\n"
                + "Numero massimo di tentativi fallibili : " + diff.getMaxFailedAttempts()
                );
        } catch (CloneNotSupportedException e) {
            System.err.println("Impossibile recuperare l'informazione richiesta");
        }
    }
    private static void handleEasyDifficulty(final Game game) {
        try {
            GameController.setEasyDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a facile.");
        } catch (SessionAlreadyStartedException err) {
            System.err.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }
    private static void handleMediumDifficulty(final Game game) {
        try {
            GameController.setMediumDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a medio.");
        } catch (SessionAlreadyStartedException err) {
            System.err.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }
    private static void handleHardDifficulty(final Game game) {
        try {
            GameController.setHardDifficulty(game);
            System.out.println("OK, livello di difficoltà impostato a difficile.");
        } catch (SessionAlreadyStartedException err) {
            System.err.println("[CH] Non puoi modificare la difficoltà durante una partita.");
         }
    }
    private static void handleShowGameGrid(final Game game) {
        try {
            Grid grid = GameController.getSessionGrid(game);
            String message = GridController.genShipMap(grid);
            System.out.println(message);
        } catch (SessionNotStartedException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("Impossibile recuperare informazioni sul livello di difficoltà");
        }
    }

    private static void handleEndSession(final Game game) {
        try {
            if (!game.isSessionStarted()) {
                throw new SessionNotStartedException();
            }
            System.out.println("Confermi? (si / no)");
            String confirm = Input.get().toLowerCase();
            switch (confirm) {
                case "si" -> {
                    System.out.println(GridController.genShipMap(game.getSessionGrid()));
                    GameController.endSession(game);
                    System.out.println("Sessione terminata");
                }
                case "no" -> System.out.println("OK, Operazione annullata");
                default -> System.err.println("Comando non riconosciuto, operazione annullata");
            }
        } catch (SessionNotStartedException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Impossibile leggere l'input");
        }
    }

    private static void handleExit() {
        try {
            System.out.println("Conferma l'uscita dal gioco (si/no)");
            String confirm = Input.get().toLowerCase();
            switch (confirm) {
                case "si" -> ExitController.getInstance().requestExit();
                case "no" -> System.out.println("OK, Operazione annullata");
                default -> System.err.println("[CH] '" + confirm + "'non e' una risposta valida, operazione annullata");
            }
        } catch (IOException err) { }
    }
    private static void setDefaultDifficulty(final Game game) {
        try {
            GameController.setEasyDifficulty(game);     //difficoltà predefinita: Facile
        } catch (SessionAlreadyStartedException e) { }
    }

}
