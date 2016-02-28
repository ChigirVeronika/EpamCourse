package com.epam.restaurant.controller.command;

/**
 * Created by Вероника on 22.01.2016.
 */
public enum CommandName {
    UNKNOWN_COMMAND,
    LOGIN_COMMAND,
    LOGOUT_COMMAND,
    REGISTER_COMMAND,

    MENU_COMMAND,
    CATEGORY_COMMAND,
    DISH_COMMAND,

    BAN_COMMAND,
    UNBAN_COMMAND,

    ADD_CATEGORY_COMMAND,
    EDIT_CATEGORY_COMMAND,
    DELETE_CATEGORY_COMMAND,
    ADD_DISH_COMMAND,

    ADD_TO_ORDER_COMMAND,
}
