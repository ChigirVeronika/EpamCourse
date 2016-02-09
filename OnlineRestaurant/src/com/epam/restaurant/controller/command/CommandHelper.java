package com.epam.restaurant.controller.command;

import com.epam.restaurant.controller.command.impl.DefaultCommand;
import com.epam.restaurant.controller.command.impl.DishesCommand;
import com.epam.restaurant.controller.command.impl.LoginCommand;
import com.epam.restaurant.controller.command.impl.LogoutCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Вероника on 04.02.2016.
 */
public class CommandHelper {
    private static final CommandHelper instance = new CommandHelper();

    private Map<CommandName,Command> commandMap = new HashMap<>();

    public CommandHelper(){
        commandMap.put(CommandName.DEFAULT_COMMAND,new DefaultCommand());

        //todo классы не реализованы
        commandMap.put(CommandName.LOGIN_COMMAND, new LoginCommand());
        commandMap.put(CommandName.LOGOUT_COMMAND,new LogoutCommand());
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
