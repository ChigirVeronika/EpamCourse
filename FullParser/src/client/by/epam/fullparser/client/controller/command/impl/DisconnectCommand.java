package client.by.epam.fullparser.client.controller.command.impl;

import client.by.epam.fullparser.client.controller.command.Command;
import client.by.epam.fullparser.client.controller.command.CommandException;
import client.by.epam.fullparser.client.service.ServiceException;
import client.by.epam.fullparser.client.bean.Request;
import client.by.epam.fullparser.client.bean.Response;
import client.by.epam.fullparser.client.service.NetworkService;
import org.apache.log4j.Logger;

public class DisconnectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( DisconnectCommand.class);
    @Override
    public Response execute(Request request) throws CommandException {
        Response response = new Response();
        try {
            NetworkService.disconnect();
            response.setSuccess("Disconnected.");
            LOGGER.info("Disconnection is successful.");
        } catch (ServiceException e) {
            LOGGER.error("Disconnect failed.", e);
        }
        return response;
    }
}
