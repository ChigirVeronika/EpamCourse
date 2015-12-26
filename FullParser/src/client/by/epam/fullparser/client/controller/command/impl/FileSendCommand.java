package client.by.epam.fullparser.client.controller.command.impl;

import client.by.epam.fullparser.client.bean.FileSendRequest;
import client.by.epam.fullparser.client.bean.FileSendResponse;
import client.by.epam.fullparser.client.bean.Response;
import client.by.epam.fullparser.client.controller.command.Command;
import client.by.epam.fullparser.client.controller.command.CommandException;
import client.by.epam.fullparser.client.service.ServiceException;
import client.by.epam.fullparser.client.bean.Request;
import client.by.epam.fullparser.client.service.NetworkService;
import server.by.epam.fullparser.server.entity.ServerRequest;
import server.by.epam.fullparser.server.entity.ServerResponse;
import org.apache.log4j.Logger;

import java.io.FileInputStream;

public class FileSendCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( FileSendCommand.class);
    @Override
    public Response execute(Request request) throws CommandException {
        FileSendResponse response = new FileSendResponse();
        FileSendRequest sendRequest = (FileSendRequest) request;
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
