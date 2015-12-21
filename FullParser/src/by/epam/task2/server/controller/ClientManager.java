package by.epam.task2.server.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();

			byte[] request =  readRequestFromClient();
			byte[] response;
			while (request[0] != 0) {
				response = responseProcessing(request);
				sendResponseToClient(response);
				request =  readRequestFromClient();
			}
			
			sendCloseConfirmationToClient();

			System.out.println("ѕоток обслуживани€ клиента завершает работу.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
}
