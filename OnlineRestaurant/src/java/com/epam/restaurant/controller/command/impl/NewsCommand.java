package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.News;
import com.epam.restaurant.service.NewsService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Show page with all news.
 */
public class NewsCommand implements Command {

    /**
     * Service provides work with database (news table)
     */
    private static final NewsService newsService = NewsService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.NEWS_JSP;

        try {
            List<News> newsList = newsService.getAllNews();

            request.setAttribute(NEWS_LIST, newsList);
        } catch (ServiceException e) {
            throw new CommandException("NewsCommand Exception", e);
        }

        return result;
    }
}
