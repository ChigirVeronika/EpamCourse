package com.epam.restaurant.service;

import com.epam.restaurant.entity.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Created by Вероника on 05.02.2016.
 */
public class UserService {
    public User login(String login, String password) throws SQLException {
        User user = get(login);
        try {
            if (user != null ){//todo && валидность пароля
                return user;
            }
        } catch (Exception e) {
            //todo Logger
        }
        return null;
    }

    public User get(String login) throws SQLException {
        //todo возьмем дао
        //todo создадим юзера по фэктори по логину

        User user = new User();
        return user;
    }
}
