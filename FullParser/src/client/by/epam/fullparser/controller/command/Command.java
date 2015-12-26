package client.by.epam.fullparser.controller.command;

import client.by.epam.fullparser.bean.Request;
import client.by.epam.fullparser.bean.Response;

public interface Command {
    Response execute(Request request) throws CommandException;
}
