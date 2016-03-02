package com.epam.restaurant.controller.command;

import com.epam.restaurant.controller.command.impl.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 02.03.2016.
 */
public class CommandHelperTest {

    @Test
    public void testGetCommand() throws Exception {
        CommandHelper commandHelper = new CommandHelper();
        assertEquals(commandHelper.getCommand("add_category_command").getClass(), AddCategoryCommand.class);
        assertEquals(commandHelper.getCommand("add_dish_command").getClass(), AddDishCommand.class);
        assertEquals(commandHelper.getCommand("ban_command").getClass(), BanCommand.class);
        assertEquals(commandHelper.getCommand("category_command").getClass(), CategoryCommand.class);
        assertEquals(commandHelper.getCommand("checkout_command").getClass(), CheckoutCommand.class);
        assertEquals(commandHelper.getCommand("delete_category_command").getClass(), DeleteCategoryCommand.class);
        assertEquals(commandHelper.getCommand("delete_dish_command").getClass(), DeleteDishCommand.class);
        assertEquals(commandHelper.getCommand("delete_item_command").getClass(), DeleteItemCommand.class);
        assertEquals(commandHelper.getCommand("dish_command").getClass(), DishCommand.class);
        assertEquals(commandHelper.getCommand("edit_category_command").getClass(), EditCategoryCommand.class);
        assertEquals(commandHelper.getCommand("edit_dish_command").getClass(), EditDishCommand.class);
        assertEquals(commandHelper.getCommand("login_command").getClass(), LoginCommand.class);
        assertEquals(commandHelper.getCommand("logout_command").getClass(), LogoutCommand.class);
        assertEquals(commandHelper.getCommand("menu_command").getClass(), MenuCommand.class);
        assertEquals(commandHelper.getCommand("register_command").getClass(), RegisterCommand.class);
        assertEquals(commandHelper.getCommand("search_user_command").getClass(), SearchUserCommand.class);
        assertEquals(commandHelper.getCommand("unban_command").getClass(), UnbanCommand.class);

    }
}