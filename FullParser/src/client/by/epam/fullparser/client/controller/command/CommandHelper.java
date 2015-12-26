package client.by.epam.fullparser.client.controller.command;

import client.by.epam.fullparser.client.controller.command.impl.DisconnectCommand;
import client.by.epam.fullparser.client.controller.command.impl.ConnectCommand;
import client.by.epam.fullparser.client.controller.command.impl.FileSendCommand;

import java.util.HashMap;

public class CommandHelper {
    private final HashMap<String, Command> commands;

    public CommandHelper() {
        commands = new HashMap<>();
        commands.put("CONNECT", new ConnectCommand());
        commands.put("SEND_FILE", new FileSendCommand());
        commands.put("DISCONNECT", new DisconnectCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
