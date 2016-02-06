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

    private Map<CommandName,ICommand> commandMap = new HashMap<>();

    public CommandHelper(){
        commandMap.put(CommandName.DEFAULT,new DefaultCommand());

        //todo классы не реализованы
        commandMap.put(CommandName.LOGIN, new LoginCommand());
        commandMap.put(CommandName.LOGOUT,new LogoutCommand());
        commandMap.put(CommandName.DISHES, new DishesCommand());
        //todo остальные комманды
    }

    public ICommand getCommand(String commandName){
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        ICommand command;
        if(null!=name){
            command = commandMap.get(name);
        }else{
            command=commandMap.get(CommandName.DEFAULT);
        }
        return command;
    }

    public static CommandHelper getInstance(){
        return instance;
    }

}
