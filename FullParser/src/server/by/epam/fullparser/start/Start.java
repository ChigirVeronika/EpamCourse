package server.by.epam.fullparser.start;

import server.by.epam.fullparser.controller.Server;

/**
 * Start point of server.
 */
public class Start {

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	
	}

}
