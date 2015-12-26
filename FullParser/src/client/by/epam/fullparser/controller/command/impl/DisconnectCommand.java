package client.by.epam.fullparser.controller.command.impl;

import client.by.epam.fullparser.controller.command.Command;
import client.by.epam.fullparser.controller.command.CommandException;
import client.by.epam.fullparser.service.ServiceException;
import client.by.epam.fullparser.bean.Request;
import client.by.epam.fullparser.bean.Response;
import client.by.epam.fullparser.service.NetworkService;
import org.apache.log4j.Logger;

public class DisconnectCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( DisconnectCommand.class);
    public DisconnectCommand(){}
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
