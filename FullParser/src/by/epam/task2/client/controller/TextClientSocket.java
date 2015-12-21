package by.epam.task2.client.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TextClientSocket {
	private static final int ANSWER_SERVER_WAIT_TIME = 1000;

	private String ip;
	private int port;

	private Socket socket;
	private InputStream in;
	private OutputStream out;

	public TextClientSocket(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void start() {
		try {
			socket = new Socket(ip, port);

			System.out.println("Клиент: соединение установлено.");

			in = socket.getInputStream();
			out = socket.getOutputStream();

			byte[] request = createRequest();
			byte[] response;
			while (request != null) {
				sendRequestToServer(request);
				response = readAnswerFromServer();
				showResponse(response);
				request = createRequest();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sendCloseRequestToServer()) {
					socket.close();
					System.out.println("Связь с сервером успешно закрыта.");
				} else {
					socket.close();
					System.out
							.println("Связь с сервером закрыта. Ответ от сервера не получен.");
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private byte[] createRequest() throws InterruptedException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter: ");
		int i = sc.nextInt();
		switch (i) {
		case 1:
			return createTextStream();
		case 0:
			return null;
		}
		return null;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private byte[] readAnswerFromServer() throws InterruptedException,
			IOException {
		int i = in.available();
		while (i == 0) {
			Thread.sleep(ANSWER_SERVER_WAIT_TIME);
			i = in.available();
		}
		byte[] b = new byte[i];
		in.read(b);
		return b;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void sendRequestToServer(byte[] b) throws IOException {
		out.write(b);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void showResponse(byte[] b) {
		String str = new String(b);
		System.out.println("server response: " + str);
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private byte[] createTextStream() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		// read text from source
		String text = "asd dfg ert";

		out.write('1');
		out.write(text.getBytes());

		byte[] request = out.toByteArray();

		out.close();

		return request;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean sendCloseRequestToServer() throws IOException,
			InterruptedException {
		out.write(new byte[] { 0 });
		byte[] response = readAnswerFromServer();
		if (response[0] == 0) {
			return true;
		}
		return false;
	}
}
