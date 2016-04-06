package com.epam.restaurant.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Check session expiration.
 */
public class SessionUtil {

    private static final String LOGIN_SESSION_ERROR = "login.session.error";

    /**
     * Check session expiration.
     * If expired, set attribute with message with this information
     * and return true
     *
     * @param request HttpServletRequest object
     * @return true if session is expired, else if not
     */
    public static boolean sessionExpired(HttpServletRequest request) {
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null) {
            String path = I18N;
            String curLan = (String) request.getSession().getAttribute(LANGUAGE);
            if (curLan != null && !curLan.equals(EN))
                path += UNDERLINE + curLan;
            ResourceBundle rb = ResourceBundle.getBundle(path);
            request.setAttribute(MESSAGE, rb.getString(LOGIN_SESSION_ERROR));
            return true;
        }
        return false;
    }
}
