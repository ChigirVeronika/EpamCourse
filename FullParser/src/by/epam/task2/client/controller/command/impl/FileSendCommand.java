package by.epam.task2.client.controller.command.impl;

import by.bsuir.lab03.client.bean.FileSendRequest;
import by.bsuir.lab03.client.bean.FileSendResponse;
import by.bsuir.lab03.client.bean.Request;
import by.bsuir.lab03.client.bean.Response;
import by.bsuir.lab03.client.controller.command.Command;
import by.bsuir.lab03.client.controller.command.CommandException;
import by.bsuir.lab03.client.service.NetworkService;
import by.bsuir.lab03.client.service.ServiceException;
import by.bsuir.lab03.entity.ServerRequest;
import by.bsuir.lab03.entity.ServerResponse;

import java.io.FileInputStream;

public class FileSendCommand implements Command {
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
            } else {
                response.setError(serverResponse.getMessage());
            }
        } catch (ServiceException e) {
            throw new CommandException("Request execute error.", e);
        }
        return response;
    }
}
