package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.Board;
import it.uniba.app.game.InitializeGame;
import it.uniba.app.utility.commands.noargs.NoArgs;
/**
 * Classe del comando /gioca.
 * Contiene il codice da eseguire con il comando /gioca.
 */
public class Gioca implements NoArgs {
    private static final String MSG_START_GAME = "Partita avviata";
    /**
     *  Il metodo inizializza una nuova partita.
     *  Se una partita è già in corso non ne viene creata una nuova.
     */
    public void execute() {
        PrintHandler.println(MSG_START_GAME);
        InitializeGame init = new InitializeGame();
        init.initGame(new Board());
    }
}
