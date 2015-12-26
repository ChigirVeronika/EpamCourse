package client.by.epam.fullparser.controller.command.impl;

import client.by.epam.fullparser.bean.FileAskRequest;
import client.by.epam.fullparser.bean.Request;
import client.by.epam.fullparser.bean.Response;
import client.by.epam.fullparser.bean.FileAskResponse;
import client.by.epam.fullparser.controller.command.Command;
import client.by.epam.fullparser.controller.command.CommandException;
import client.by.epam.fullparser.service.ServiceException;
import client.by.epam.fullparser.service.NetworkService;
import entity.ServerRequest;
import entity.ServerResponse;
import org.apache.log4j.Logger;

import java.io.FileInputStream;

public class FileAskCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( FileAskCommand.class);
    @Override
    public Response execute(Request request) throws CommandException {
        FileAskResponse response = new FileAskResponse();
        FileAskRequest sendRequest = (FileAskRequest) request;
        try {
            ServerRequest serverRequest = new ServerRequest();
            serverRequest.setFileLength(sendRequest.getFileLength());
            serverRequest.setParserType(sendRequest.getParserType());
            NetworkService.send(serverRequest);
            FileInputStream fin = sendRequest.getFileInputStream();
            NetworkService.send(fin, sendRequest.getFileLength());

            ServerResponse serverResponse = NetworkService.receive();
            if (serverResponse.isSuccess()) {
                response.setSuccess(serverResponse.getMessage());
                response.setContacts(serverResponse.getBooks());
                LOGGER.info("File sent successfully.");
            } else {
                response.setError(serverResponse.getMessage());
                LOGGER.info("Trouble with server response. Wait and try again.");
            }
        } catch (ServiceException e) {
            LOGGER.error("Request execute error.", e);
        }
        return response;
    }
}
