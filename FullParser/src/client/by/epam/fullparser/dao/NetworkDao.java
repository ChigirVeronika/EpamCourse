package client.by.epam.fullparser.dao;

import entity.ServerResponse;

import java.io.*;
import java.net.Socket;

public class NetworkDao {
    private static final NetworkDao instance = new NetworkDao();

    private Socket socket;
    private OutputStream out;
    private InputStream in;

    private NetworkDao() {}

    public static NetworkDao getInstance() {
        return instance;
    }

    public void connectTo(String host, int port) throws DaoException {
        try {
            socket = new Socket(host, port);
            out = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (IOException e) {
            throw new DaoException("Connection failed.", e);
        }
    }

    public void sendObject(Object object) throws DaoException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(this.out);
            out.writeObject(object);
        } catch (IOException e) {
            throw new DaoException("Send object error.", e);
        }

    }

    public void sendBytes(byte[] bytes) throws DaoException {
        try {
            out.write(bytes);
        } catch (IOException e) {
            throw new DaoException("Can't send byte array.", e);
        }
    }

    public ServerResponse receiveFromServer() throws DaoException {
        ServerResponse response;
        try {
            ObjectInputStream in = new ObjectInputStream(this.in);
            response = (ServerResponse) in.readObject();
        } catch (IOException|ClassNotFoundException e) {
            throw new DaoException("Receiving data error.", e);
        }
        return response;
    }

    public void sendLong(long longValue) throws DaoException {
        try {
            DataOutputStream out = new DataOutputStream(this.out);
            out.writeLong(longValue);
        } catch (IOException e) {
            throw new DaoException("Send long error.", e);
        }
    }

    public void disconnect() throws DaoException {
        try {
            socket.close();
        } catch (IOException e) {
            throw new DaoException("Can't disconnect.", e);
        }
    }
}
