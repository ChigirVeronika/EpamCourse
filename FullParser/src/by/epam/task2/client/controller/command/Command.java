package by.epam.task2.client.controller.command;

import by.epam.task2.client.bean.Request;
import by.epam.task2.client.bean.Response;

public interface Command {
    Response execute(Request request) throws CommandException;
}
