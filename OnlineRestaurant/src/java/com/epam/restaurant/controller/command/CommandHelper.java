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
        commandMap.put(CommandName.UNKNOWN_COMMAND,new UnknownCommand());
        commandMap.put(CommandName.LOGIN_COMMAND, new LoginCommand());
        commandMap.put(CommandName.LOGOUT_COMMAND,new LogoutCommand());
        commandMap.put(CommandName.REGISTER_COMMAND,new RegisterCommand());

        commandMap.put(CommandName.MENU_COMMAND, new MenuCommand());
        commandMap.put(CommandName.CATEGORY_COMMAND, new CategoryCommand());
        commandMap.put(CommandName.DISH_COMMAND, new DishCommand());
        commandMap.put(CommandName.CHECKOUT_COMMAND, new CheckoutCommand());

        commandMap.put(CommandName.BAN_COMMAND,new BanCommand());
        commandMap.put(CommandName.UNBAN_COMMAND,new UnbanCommand());
        commandMap.put(CommandName.SEARCH_USER_COMMAND,new SearchUserCommand());

        commandMap.put(CommandName.ADD_CATEGORY_COMMAND,new AddCategoryCommand());
        commandMap.put(CommandName.EDIT_CATEGORY_COMMAND,new EditCategoryCommand());
        commandMap.put(CommandName.DELETE_CATEGORY_COMMAND, new DeleteCategoryCommand());
        commandMap.put(CommandName.ADD_DISH_COMMAND,new AddDishCommand());
        commandMap.put(CommandName.EDIT_DISH_COMMAND,new EditDishCommand());
        commandMap.put(CommandName.DELETE_DISH_COMMAND,new DeleteDishCommand());

        commandMap.put(CommandName.ADD_TO_ORDER_COMMAND, new AddToOrderCommand());
        commandMap.put(CommandName.DELETE_ITEM_COMMAND,new DeleteItemCommand());
        commandMap.put(CommandName.UPDATE_ORDER_COMMAND,new UpdateOrderCommand());

    }

    public Command getCommand(String commandName){
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if(null!=name){
            command = commandMap.get(name);
        }else{
            command=commandMap.get(CommandName.UNKNOWN_COMMAND);
        }
        return command;
    }

    public static CommandHelper getInstance(){
        return instance;
    }

}
