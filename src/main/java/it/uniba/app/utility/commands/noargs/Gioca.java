package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.BoardManager;
import it.uniba.app.game.InitializeGame;
import it.uniba.app.utility.PrintHandler;
/**
 * Classe del comando /gioca.
 * Contiene il codice da eseguire con il comando /gioca.
 */
public class Gioca implements NoArgs {
    private static final String MSG_START_GAME = "Partita avviata";
    private static final String MSG_GAME_ALREADY_STARTED = "Partita già avviata";

    /**
     *  Il metodo inizializza una nuova partita.
     *  Se una partita è già in corso non ne viene creata una nuova.
     */
    public void execute() {
        if (InitializeGame.isGameRunning()) {
            PrintHandler.println(MSG_GAME_ALREADY_STARTED);
        } else {
            PrintHandler.println(MSG_START_GAME);
            InitializeGame init = new InitializeGame();
            init.initGame(BoardManager.access());
            PrintHandler.printPlayerMap(BoardManager.access());
        }
    }
}
