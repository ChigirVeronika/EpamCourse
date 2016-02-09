package main.com.epam.site.controller.command.impl;

import main.com.epam.site.controller.JspPageName;
import main.com.epam.site.controller.RequestParameterName;
import main.com.epam.site.controller.command.CommandException;
import main.com.epam.site.controller.command.ICommand;
import main.com.epam.site.dao.XMLDao;
import main.com.epam.site.dao.XMLDaoException;
import main.com.epam.site.dao.XMLDaoFactory;
import main.com.epam.site.service.MyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Вероника on 22.01.2016.
 */
public class DoAnythindCommand implements ICommand {
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        List<Object> info = MyService.service(request.getParameter(RequestParameterName.FILE_NAME));
        request.setAttribute(RequestParameterName.SIMPLE_INFO, info);
        page= JspPageName.USER_PAGE;

        return page;
    }
}
