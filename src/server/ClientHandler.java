package server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private List<ClientHandler> clients;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            username = in.readLine();
            broadcast("🔔 " + username + " a rejoint le chat.");

            String msg;
            while ((msg = in.readLine()) != null) {
                broadcast(username + ": " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            clients.remove(this);
            broadcast("❌ " + username + " a quitté le chat.");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void broadcast(String msg) {
        for (ClientHandler c : clients) {
            c.out.println(msg);
        }
    }
}