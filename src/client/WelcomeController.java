package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class WelcomeController {
    @FXML private TextField nameField;

    @FXML
    private void startChat() {
        try {
            String username = nameField.getText().trim();
            if (username.isEmpty()) return;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
            Parent root = loader.load();

            ChatController controller = loader.getController();
            controller.initUsername(username);

            Stage stage = new Stage();
            stage.setTitle("Chat - " + username);
            stage.setScene(new Scene(root));
            stage.show();

            nameField.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}