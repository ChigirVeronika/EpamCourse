package client.by.epam.fullparser.controller.command;

import client.by.epam.fullparser.controller.command.impl.DisconnectCommand;
import client.by.epam.fullparser.controller.command.impl.ConnectCommand;
import client.by.epam.fullparser.controller.command.impl.FileAskCommand;

import java.util.HashMap;

public class CommandHelper {
    private final HashMap<String, Command> commands;

    public CommandHelper() {
        commands = new HashMap<>();
        commands.put("CONNECT", new ConnectCommand());
        commands.put("PARSE_FILE", new FileAskCommand());
        commands.put("DISCONNECT", new DisconnectCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
