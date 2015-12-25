package by.epam.task2.client.controller.command.impl;


import by.epam.task2.client.bean.ConnectRequest;
import by.epam.task2.client.bean.Request;
import by.epam.task2.client.bean.Response;
import by.epam.task2.client.controller.command.Command;
import by.epam.task2.client.controller.command.CommandException;
import by.epam.task2.server.service.ServiceException;

public class ConnectCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        try {
            ConnectRequest connectRequest = (ConnectRequest) request;
            NetworkService.connect(connectRequest.getHost(), connectRequest.getPort());
        } catch (ServiceException |ClassCastException e) {
            throw new CommandException("Command executing failed.", e);
        }
        Response response = new Response();
        response.setSuccess("Connected to server.");
        return response;
    }
}
