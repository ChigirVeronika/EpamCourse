package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.dao.sql.SqlDao;
import com.epam.restaurant.dao.sql.SqlDaoException;
import com.epam.restaurant.dao.sql.SqlDaoFactory;
import com.epam.restaurant.controller.command.CommandException;
import com.epam.restaurant.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Вероника on 04.02.2016.
 */
public class DishesCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page = null;
        SqlDao dao = null;
        try{
            dao = SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.SAX);
            List<Object> info = dao.parse(request.getParameter(RequestParameterName.FILE_NAME));
            request.setAttribute(RequestParameterName.SIMPLE_INFO, info);
            page= JspPageName.DISHES_JSP;
        }catch (SqlDaoException e){
            throw new CommandException("Can't get dao.",e);
        }
        return page;
    }
}
