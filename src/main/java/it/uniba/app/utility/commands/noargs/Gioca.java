package it.uniba.app.utility.commands.noargs;
import it.uniba.app.game.Board;
import it.uniba.app.game.InitializeGame;
import it.uniba.app.utility.commands.noargs.NoArgs;
public class Gioca implements NoArgs {
    public void execute() {
        InitializeGame init = new InitializeGame();
        init.initGame(new Board());
    }
}
