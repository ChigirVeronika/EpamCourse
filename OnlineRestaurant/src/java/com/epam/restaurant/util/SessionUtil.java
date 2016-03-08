package com.epam.restaurant.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 *
 */
public class SessionUtil {
    public static boolean sessionExpired(HttpServletRequest request){
        HttpSession currentSession = request.getSession(false);
        if(currentSession==null){
            String path = I18N;
            String curLan = (String) request.getSession().getAttribute(LANGUAGE);
            if (curLan != null && !curLan.equals(EN))
                path += UNDERLINE + curLan;
            ResourceBundle rb = ResourceBundle.getBundle(path);
            request.setAttribute(MESSAGE, rb.getString("login.session.error"));
            return true;
        }
        return false;
    }
}
