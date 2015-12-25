package by.epam.task2.client.controller;

import by.epam.task2.client.bean.Request;
import by.epam.task2.client.bean.Response;
import by.bsuir.lab03.client.controller.command.CommandException;
import by.bsuir.lab03.client.controller.command.CommandHelper;

public class Controller {
    private final CommandHelper helper = new CommandHelper();

    public Response execute(Request request) {
        Response response;
        try {
            response = helper.getCommand(request.getCommand()).execute(request);
        } catch (CommandException e) {
            response = new Response();
            response.setError("Request execute failed.");
        }
        return response;
    }
}
