package client.by.epam.fullparser.controller.command.impl;


import client.by.epam.fullparser.bean.ConnectRequest;
import client.by.epam.fullparser.bean.Request;
import client.by.epam.fullparser.bean.Response;
import client.by.epam.fullparser.controller.command.Command;
import client.by.epam.fullparser.controller.command.CommandException;
import client.by.epam.fullparser.service.NetworkService;
import client.by.epam.fullparser.service.ServiceException;
import org.apache.log4j.Logger;

public class ConnectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( ConnectCommand.class);
    public ConnectCommand(){}
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
