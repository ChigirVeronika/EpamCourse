package main.com.epam.site.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Вероника on 22.01.2016.
 */
public interface ICommand {
    String execute(HttpServletRequest request) throws CommandException;
}
