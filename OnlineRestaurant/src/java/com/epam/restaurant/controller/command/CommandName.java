package com.epam.restaurant.controller.command;

/**
 * Contains all commands names.
 */
public enum CommandName {
    UNKNOWN_COMMAND,
    LOGIN_COMMAND,
    LOGOUT_COMMAND,
    REGISTER_COMMAND,

    MENU_COMMAND,
    CATEGORY_COMMAND,
    DISH_COMMAND,
    CHECKOUT_COMMAND,

    BAN_COMMAND,
    UNBAN_COMMAND,
    SEARCH_USER_COMMAND,

    ADD_CATEGORY_COMMAND,
    EDIT_CATEGORY_COMMAND,
    DELETE_CATEGORY_COMMAND,

    ADD_DISH_COMMAND,
    EDIT_DISH_COMMAND,
    DELETE_DISH_COMMAND,

    ADD_TO_ORDER_COMMAND,
    DELETE_ITEM_COMMAND,
    UPDATE_ORDER_COMMAND,

    NEWS_COMMAND
}
