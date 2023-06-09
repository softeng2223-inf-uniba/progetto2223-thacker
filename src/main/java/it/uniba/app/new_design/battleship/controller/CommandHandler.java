package it.uniba.app.new_design.battleship.controller;

public final class CommandHandler {

    private CommandHandler() { }

    public void execute() {
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
}
