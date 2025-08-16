package client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.Socket;

public class ChatController {
    @FXML private TextField messageField;
    @FXML private Button sendButton;
    @FXML private VBox messageVBox;
    @FXML private ScrollPane scrollPane;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;

    public void initUsername(String name) {
        this.username = name;
        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", 9090);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(username);

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        String finalMsg = msg;
                        Platform.runLater(() -> addMessage(finalMsg));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sendMessage() {
        String msg = messageField.getText().trim();
        if (!msg.isEmpty()) {
            out.println(msg);
            messageField.clear();
        }
    }

    private void addMessage(String message) {
        TextFlow flow = new TextFlow(new Text(message));
        flow.setStyle("-fx-background-color: lightgray; -fx-background-radius: 10; -fx-padding: 5;");
        messageVBox.getChildren().add(flow);
    }
}