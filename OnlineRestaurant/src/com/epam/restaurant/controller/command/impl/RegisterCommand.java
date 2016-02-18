package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.UserService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 *Control users registration to site.
 */
public class RegisterCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( RegisterCommand.class);

    private static UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String name=request.getParameter(NAME);
        String surname=request.getParameter(SURNAME);
        String login=request.getParameter(LOGIN);
        String password=request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        String payCard = request.getParameter(PAY_CARD_ID);

        String result = JspPageName.REGISTER_JSP;
        try{
            if(userService.get(login)==null){
                User user = userService.create(name, surname, email, payCard, login, password);
                result = JspPageName.LOGIN_JSP;//зарегался - теперь залогинься
            }else {
                String path =I18N;
                String currentLanguage = (String) request.getSession().getAttribute(LANGUAGE);
                if(currentLanguage!=null && !currentLanguage.equals("en")){
                    path+="_"+currentLanguage;
                }
                ResourceBundle rb = ResourceBundle.getBundle(path);
                request.setAttribute(LOGIN_MESSAGE,rb.getString("register.wrong"));
            }
        }catch (ServiceException e){
            throw new CommandException("Registration failed.");
        }

        return result;
    }
}
