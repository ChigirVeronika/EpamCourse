package server.by.epam.fullparser.server.controller;

import server.by.epam.fullparser.server.entity.Book;
import server.by.epam.fullparser.server.entity.ServerResponse;
import server.by.epam.fullparser.server.service.ParseService;
import server.by.epam.fullparser.server.service.ServiceException;
import server.by.epam.fullparser.server.entity.ServerRequest;
import server.by.epam.fullparser.server.service.FileService;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientManager implements Runnable {
	
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	
	public ClientManager(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String fileName = "";
		ServerResponse response = new ServerResponse();
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();

			ServerRequest request = getRequest();
			fileName = storeFile(request);
			List<Book> books = ParseService.parse(request.getParserType(), fileName);

			response.setBooks(books);
			response.setSuccess("Parse completed.");
		} catch (IOException | ServiceException e) {
			response.setError("Can't parse this file. File is invalid.");
			System.err.println(e.getMessage());
		} finally {
			try {
				ObjectOutputStream objOut = new ObjectOutputStream(out);
				objOut.writeObject(response);
			} catch (IOException e) {

			}
			try {
				socket.close();
			} catch (IOException e) {}
			try {
				FileService.delete(fileName);
			} catch (ServiceException e) {}
		}
	}

	private String storeFile(ServerRequest request) {
		String fileName = "";
		int toRead = 2048;
		try {
			FileService.createFile();
			long length = request.getFileLength();
			long read = 0;
			if (length < toRead) {
				toRead = (int)length;
			}
			byte[] bytes = new byte[toRead];
			while (read < length) {
				int readFromSocket = in.read(bytes);
				read += readFromSocket;
				FileService.writeToFile(bytes);
				length -= read;
				if (length < toRead){
					bytes = new byte[(int)length];
				}
			}
			fileName = FileService.closeFile();
		} catch (IOException|ServiceException e) {
			System.err.println(e.getMessage());
		}
		return fileName;
	}

	private ServerRequest getRequest() {
		ServerRequest request;
		try {
			while (this.in.available() == 0) {
				Thread.sleep(1000);
			}

			ObjectInputStream in = new ObjectInputStream(this.in);
			request = (ServerRequest) in.readObject();

		} catch (IOException|ClassNotFoundException|InterruptedException e) {
			return null;
		}
		return request;
	}

}
