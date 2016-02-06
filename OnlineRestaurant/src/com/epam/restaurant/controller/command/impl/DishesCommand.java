package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.dao.sql.SqlDao;
import com.epam.restaurant.dao.sql.SqlDaoException;
import com.epam.restaurant.dao.sql.SqlDaoFactory;
import com.epam.restaurant.controller.command.CommandException;
import com.epam.restaurant.controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Вероника on 04.02.2016.
 */
public class DishesCommand implements ICommand {

    private static final Logger LOGGER = Logger.getLogger( DishesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String page;
        SqlDao dao;
        try{
            dao = SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.SAX);
            LOGGER.info("dao created");
            List<Object> info = dao.parse(request.getParameter(RequestParameterName.FILE_NAME));
            LOGGER.info("list created");
            request.setAttribute(RequestParameterName.SIMPLE_INFO, info);
            LOGGER.info("request attribute created");
            page= JspPageName.DISHES_JSP;
        }catch (SqlDaoException e){
            LOGGER.error("can't get dao ",e);
            page = JspPageName.ERROR_JSP;
        }
        return page;
    }
}
