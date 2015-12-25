package by.epam.task2.client.controller.command;

import by.bsuir.lab03.client.bean.Request;
import by.bsuir.lab03.client.bean.Response;

public interface Command {
    Response execute(Request request) throws CommandException;
}
