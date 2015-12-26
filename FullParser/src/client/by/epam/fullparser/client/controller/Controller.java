package client.by.epam.fullparser.client.controller;

import client.by.epam.fullparser.client.bean.Request;
import client.by.epam.fullparser.client.bean.Response;
import client.by.epam.fullparser.client.controller.command.CommandException;
import client.by.epam.fullparser.client.controller.command.CommandHelper;
import org.apache.log4j.Logger;


public class Controller {
    private static final Logger LOGGER = Logger.getLogger( Controller.class);
    private final CommandHelper helper = new CommandHelper();

    public Response execute(Request request) {
        Response response;
        try {
            response = helper.getCommand(request.getCommand()).execute(request);
            LOGGER.info("Response execute successful.");
        } catch (CommandException e) {
            response = new Response();
            response.setError("Request execute failed.");
            LOGGER.error(e);
        }
        return response;
    }
}
