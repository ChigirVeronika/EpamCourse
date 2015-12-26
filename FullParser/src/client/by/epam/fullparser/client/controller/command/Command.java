package client.by.epam.fullparser.client.controller.command;

import client.by.epam.fullparser.client.bean.Request;
import client.by.epam.fullparser.client.bean.Response;

public interface Command {
    Response execute(Request request) throws CommandException;
}
