package it.uniba.app.utility.commands;

import it.uniba.app.utility.commands.noargs.*;
import java.util.HashMap;
import java.util.Map;
public class FlagHandler {
    private static final String MSG_FLAG_NOT_RECOGNIZED = "Flag non riconosciuto";
    private static final Map<String, NoArgs> flags = new HashMap<>();
    static{
        flags.put("--help", new HelpCommand());
        flags.put("-h", new HelpCommand());
    }
}
