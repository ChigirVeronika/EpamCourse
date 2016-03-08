package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.service.NewsService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import static com.epam.restaurant.util.SessionUtil.*;
/**
 *
 */
public class AddNewsCommand implements Command {

    private static final NewsService newsService = NewsService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.NEWS_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        try {
            String newsName = request.getParameter("name");
            String newsContent = request.getParameter("content");
            String newsImage = request.getParameter("image");
            Date date = new Date();

            newsService.create(newsName, date, newsContent, newsImage);

        } catch (ServiceException e) {
            throw new CommandException("Cant't execute AddDishCommand");
        }


        return result;
    }
}
