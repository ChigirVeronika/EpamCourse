package main.com.epam.site.service.impl;

import main.com.epam.site.controller.JspPageName;
import main.com.epam.site.service.CommandException;
import main.com.epam.site.service.ICommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Вероника on 22.01.2016.
 */
public class NoSuchCommand implements ICommand{

    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
