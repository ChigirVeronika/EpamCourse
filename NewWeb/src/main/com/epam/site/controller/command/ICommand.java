package main.com.epam.site.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Вероника on 22.01.2016.
 */
public interface ICommand {
    String execute(HttpServletRequest request) throws CommandException;
}
