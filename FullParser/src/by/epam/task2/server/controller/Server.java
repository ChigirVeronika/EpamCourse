package by.epam.task2.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server;

	public void start() {
		try {
			server = new ServerSocket(4825);

			while (true) {
				System.out.println("������� �����������.");
				Socket socket = server.accept();

				Thread newClient = new Thread(new ClientManager(socket));
				newClient.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
