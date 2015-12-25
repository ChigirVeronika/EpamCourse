package by.epam.task2.client.controller.command;

import by.bsuir.lab03.client.controller.command.impl.ConnectCommand;
import by.bsuir.lab03.client.controller.command.impl.DisconnectCommand;
import by.bsuir.lab03.client.controller.command.impl.FileSendCommand;

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
