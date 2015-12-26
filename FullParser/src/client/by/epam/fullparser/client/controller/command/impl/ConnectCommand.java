package client.by.epam.fullparser.client.controller.command.impl;


import client.by.epam.fullparser.client.bean.ConnectRequest;
import client.by.epam.fullparser.client.bean.Request;
import client.by.epam.fullparser.client.bean.Response;
import client.by.epam.fullparser.client.controller.command.Command;
import client.by.epam.fullparser.client.controller.command.CommandException;
import client.by.epam.fullparser.client.service.NetworkService;
import client.by.epam.fullparser.client.service.ServiceException;
import org.apache.log4j.Logger;

public class ConnectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( ConnectCommand.class);
    @Override
    public Response execute(Request request) throws CommandException {
        try {
            ConnectRequest connectRequest = (ConnectRequest) request;
            NetworkService.connect(connectRequest.getHost(), connectRequest.getPort());
            LOGGER.info("Connection to server is successful.");
        } catch (ServiceException |ClassCastException e) {
            LOGGER.error("Command executing failed.", e);
        }
        Response response = new Response();
        response.setSuccess("Connected to server.");
        return response;
    }
}
