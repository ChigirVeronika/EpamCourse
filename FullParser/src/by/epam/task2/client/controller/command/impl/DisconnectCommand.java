package by.epam.task2.client.controller.command.impl;

import by.epam.task2.client.bean.Request;
import by.epam.task2.client.bean.Response;
import by.epam.task2.client.controller.command.Command;
import by.epam.task2.client.controller.command.CommandException;
import by.epam.task2.client.service.NetworkService;
import by.epam.task2.client.service.ServiceException;

public class DisconnectCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        Response response = new Response();
        try {
            NetworkService.disconnect();
            response.setSuccess("Disconnected.");
        } catch (ServiceException e) {
            throw new CommandException("Disconnect failed.", e);
        }
        return response;
    }
}
