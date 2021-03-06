package server.by.epam.fullparser.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final static int PORT = 58457;//4825
	private ServerSocket serverSocket;

	public Server(){}

	public void start() {
		try {
			serverSocket = new ServerSocket(PORT);
			while (true) {
				System.out.println("Waiting connection...");
				Socket socket = serverSocket.accept();

				new Thread(new ClientManager(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
