package by.epam.task2.client.start;

import by.epam.task2.client.controller.TextClientSocket;

public class Start {

	public static void main(String[] args) {
		TextClientSocket client = new TextClientSocket("localhost", 4825);
		client.start();

	}

}
