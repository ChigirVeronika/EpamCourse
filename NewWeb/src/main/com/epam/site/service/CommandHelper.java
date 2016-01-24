package main.com.epam.site.service;

import main.com.epam.site.service.impl.DoAnythindCommand;
import main.com.epam.site.service.impl.NoSuchCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Вероника on 22.01.2016.
 */
public class CommandHelper {
    private static final CommandHelper instance = new CommandHelper();

    private Map<CommandName,ICommand> commands = new HashMap<CommandName, ICommand>();

    public CommandHelper(){
        commands.put(CommandName.DO_ANYTHING_COMMAND, new DoAnythindCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }

    public static CommandHelper getInstance(){
        return instance;
    }

    public ICommand getCommand(String commandName){
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        ICommand command;
        if(null!=name){
            command = commands.get(name);
        }else{
            command=commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}
