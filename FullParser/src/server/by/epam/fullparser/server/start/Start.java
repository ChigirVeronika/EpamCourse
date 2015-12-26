package server.by.epam.fullparser.server.start;

import server.by.epam.fullparser.server.controller.Server;

/**
 * Start point of server.
 */
public class Start {

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	
	}

}
