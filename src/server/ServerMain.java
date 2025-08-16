package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        List<ClientHandler> clients = new CopyOnWriteArrayList<>();

        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket, clients);
            clients.add(handler);
            handler.start();
        }
    }
}