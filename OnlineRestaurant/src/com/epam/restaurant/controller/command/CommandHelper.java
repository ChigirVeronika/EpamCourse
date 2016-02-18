package com.epam.restaurant.controller.command;

import com.epam.restaurant.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Command pattern realization for all commands.
 */
public final class CommandHelper {
    private static final CommandHelper instance = new CommandHelper();

    private final Map<CommandName,Command> commandMap = new HashMap<>();

    public CommandHelper(){
        commandMap.put(CommandName.DEFAULT_COMMAND,new DefaultCommand());
        commandMap.put(CommandName.LOGIN_COMMAND, new LoginCommand());
        commandMap.put(CommandName.LOGOUT_COMMAND,new LogoutCommand());
        commandMap.put(CommandName.REGISTER_COMMAND,new RegisterCommand());
        commandMap.put(CommandName.BAN_COMMAND,new BanCommand());
        commandMap.put(CommandName.UNBAN_COMMAND,new UnbanCommand());
        commandMap.put(CommandName.DISHES_COMMAND, new DishesCommand());
        //todo остальные комманды
    }

    public Command getCommand(String commandName){
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if(null!=name){
            command = commandMap.get(name);
        }else{
            command=commandMap.get(CommandName.DEFAULT_COMMAND);
        }
        return command;
    }

    public static CommandHelper getInstance(){
        return instance;
    }

}
