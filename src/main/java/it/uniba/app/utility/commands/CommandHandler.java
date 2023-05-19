package it.uniba.app.utility.commands;

import it.uniba.app.utility.commands.withargs.*;
import it.uniba.app.utility.commands.noargs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private static final String ENCODER_USED = "UTF-8";
    private static final String SYMBOL_INPUT_PROMPT = "> ";
    private static final String MSG_COMMAND_NOT_RECOGNIZED = "Comando non riconosciuto";
}
