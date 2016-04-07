package com.epam.restaurant.util;

import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate input parameters of service layer.
 */
public class ValidationUtil {

    private static final String USER_NAME = "^[A-Za-zА-Яа-я]+$";
    private static final String USER_SURNAME = "^[A-Za-zА-Яа-я\\-]+$";
    private static final String USER_EMAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    private static final String USER_PAYCARD = "^[0-9]+$";
    private static final String USER_LOGIN = "^[0-9a-zA-ZА-Яа-я\\-]+$";

    private static final String DISH_NAME = "^[0-9a-zA-ZА-Яа-я\\-\\s]+";
    private static final String DISH_PRICE = "^[0-9]+\\.*[0-9]*$";
    private static final String DISH_QUANTITY = "^[0-9]+$";
    private static final String DISH_IMAGE = "^[0-9a-zA-ZА-Яа-я\\-]+\\.[a-zA-Z]+$";

    private static final String EMPTY_STRING = "";

    /**
     * Check if value matched pattern
     *
     * @param pattern
     * @param value
     * @return true if matched, false if not
     */
    private static boolean validValue(String pattern, String value) {
        Pattern patternForName = Pattern.compile(pattern);
        Matcher matcherName = patternForName.matcher(value);
        if (matcherName.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if category object is valid
     *
     * @param category
     * @return true if valid, false if not
     */
    public static boolean categoryValid(Category category) {
        if (category.getName().equals(EMPTY_STRING)) {
            return false;
        }
        if (category.getDescription().equals(EMPTY_STRING)) {
            return false;
        }
        return true;
    }

    /**
     * Check if dish object is valid
     *
     * @param dish
     * @return true if valid, false if not
     */
    public static boolean dishValid(Dish dish) {
        if (!validValue(DISH_NAME, dish.getName())) {
            return false;
        }
        if (!validValue(DISH_PRICE, dish.getPrice().toString())) {
            return false;
        }
        if (!validValue(DISH_QUANTITY, String.valueOf(dish.getQuantity()))) {
            return false;
        }
        if (!validValue(DISH_IMAGE, dish.getImage())) {
            return false;
        }
        if (dish.getIngredients().equals(EMPTY_STRING)) {
            return false;
        }
        if (dish.getDescription().equals(EMPTY_STRING)) {
            return false;
        }

        return true;
    }

    /**
     * Check if user object is valid
     *
     * @param user
     * @return true if valid, false if not
     */
    public static boolean userValid(User user) {
        if (!validValue(USER_NAME, user.getName())) {
            return false;
        }
        if (!validValue(USER_SURNAME, user.getSurname())) {
            return false;
        }
        if (!validValue(USER_EMAIL, user.getEmail())) {
            return false;
        }
        if (!validValue(USER_PAYCARD, user.getPayCard())) {
            return false;
        }
        if (!validValue(USER_LOGIN, user.getLogin())) {
            return false;
        }
        return true;
    }
}
