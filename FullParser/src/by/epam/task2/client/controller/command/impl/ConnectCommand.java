package by.epam.task2.client.controller.command.impl;

import by.bsuir.lab03.client.bean.ConnectRequest;
import by.bsuir.lab03.client.bean.Request;
import by.bsuir.lab03.client.bean.Response;
import by.bsuir.lab03.client.controller.command.Command;
import by.bsuir.lab03.client.controller.command.CommandException;
import by.bsuir.lab03.client.service.NetworkService;
import by.bsuir.lab03.client.service.ServiceException;

public class ConnectCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        try {
            ConnectRequest connectRequest = (ConnectRequest) request;
            NetworkService.connect(connectRequest.getHost(), connectRequest.getPort());
        } catch (ServiceException|ClassCastException e) {
            throw new CommandException("Command executing failed.", e);
        }
        Response response = new Response();
        response.setSuccess("Connected to server.");
        return response;
    }
}
