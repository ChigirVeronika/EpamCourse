package by.epam.task2.server.controller;

import by.epam.task2.entity.Book;
import by.epam.task2.entity.ServerRequest;
import by.epam.task2.entity.ServerResponse;
import by.epam.task2.server.service.ParseService;
import by.epam.task2.server.service.FileService;
import by.epam.task2.server.service.ServiceException;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientManager implements Runnable {
	private static final int ANSWER_CLIENT_WAIT_TIME = 1000;
	
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

	private byte[] readRequestFromClient() throws InterruptedException,
			IOException {
		int i = in.available();
		while (i == 0) {
			Thread.sleep(ANSWER_CLIENT_WAIT_TIME);
			i = in.available();
		}
		byte[] b = new byte[i];
		in.read(b);
		return b;
	}
	
	private byte[] responseProcessing(byte[] request) throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		String text = "server answer";

		out.write(request[0]);
		out.write(text.getBytes());

		byte[] response = out.toByteArray();

		out.close();

		return response;
	}

	private void sendResponseToClient(byte[] response) throws IOException{
		out.write(response);
	}

	private void sendCloseConfirmationToClient() throws IOException{
		out.write(new byte[]{0});
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
