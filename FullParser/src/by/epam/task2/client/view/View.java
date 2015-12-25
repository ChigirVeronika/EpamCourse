package by.epam.task2.client.view;

import by.epam.task2.client.bean.*;
import by.epam.task2.client.controller.Controller;
import by.epam.task2.entity.Book;
import by.epam.task2.entity.ParserType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Вероника on 26.12.2015.
 */
public class View {
    private final Controller controller = new Controller();

    public void start() {
        showMainCommands();
        String command = readCommand();
        while (!"exit".equals(command)) {
            if ("send".equals(command)) {
                connectToServer("localhost", 58457);
                FileSendRequest request = readRequestData();
                Response response = controller.execute(request);
                if (response.isSuccess()) {
                    showResponseData((FileSendResponse) response);
                } else {
                    System.err.println(response.getMessage());
                }
                disconnect();
                showMainCommands();
            }
            command = readCommand();
        }
    }

    private void disconnect() {
        Request request = new Request();
        request.setCommand("DISCONNECT");
        Response response = controller.execute(request);
        System.out.println(response.getMessage() + "\n");
    }

    private void showResponseData(FileSendResponse response) {
        List<Book> books = response.getBooks();
        books.forEach(System.out::println);
    }

    private FileSendRequest readRequestData() {
        FileSendRequest request = new FileSendRequest();
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPath to XML document: ");
        String path = scanner.nextLine();
        while (!Files.exists(Paths.get(path)) || "".equals(path)) {
            System.out.println("This file doesn't exist. Check file path.");
            System.out.print("\nPath to XML document: ");
            path = scanner.nextLine();
        }

        System.out.println("\nChoose parser type:");
        System.out.println("\tdom\t\tDOM parser");
        System.out.println("\tsax\t\tSAX parser");
        System.out.println("\tstax\tStAX parser");
        String type = readCommand();
        while (!"dom".equals(type) && !"sax".equals(type) && !"stax".equals(type)) {
            System.out.println("Wrong parser type. Try again.");
            type = readCommand();
        }

        try {
            ParserType parserType = ParserType.valueOf(type.toUpperCase());
            File file = new File(path);
            long length = file.length();
            FileInputStream fileInputStream = new FileInputStream(file);
            request.setParserType(parserType);
            request.setCommand("SEND_FILE");
            request.setFileInputStream(fileInputStream);
            request.setFileLength(length);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found! " + e.getMessage());
        }

        return request;
    }

    private String readCommand() {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    private void showMainCommands() {
        System.out.println("Commands:");
        System.out.println("\tsend\tParse XML");
        System.out.println("\texit\tClose app");
    }

    private void connectToServer(String host, int port) {
        ConnectRequest request = new ConnectRequest();
        request.setCommand("CONNECT");
        request.setHost(host);
        request.setPort(port);

        System.out.println("Connecting to " + host + ":" + port);

        Response response = controller.execute(request);

        System.out.println(response.getMessage());
    }
}
