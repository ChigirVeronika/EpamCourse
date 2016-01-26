package by.epam.websocket.service;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Can send http requests and get responses.
 */
public class HttpClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT =8080;
    private static final Logger LOGGER = Logger.getLogger( HttpClient.class);

    public HttpClient(){}

    public String sendRequest(String request){
        String response=null;
        try {
            Socket socket = new Socket(HOST,PORT);
            LOGGER.info("Connected to server");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            //System.out.println("ADDED:"+request);
            out.write(request.getBytes());

            int i = in.available();
            while (i == 0) {
                Thread.sleep(50);
                i = in.available();
            }
            byte[] b = new byte[i];
            in.read(b);

            response=new String(b);
            LOGGER.info("Terminated");
        } catch (IOException e) {
            LOGGER.error(e);
        }catch (InterruptedException e){
            LOGGER.error(e);
        }
        return response;

    }
}
