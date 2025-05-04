package org.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.*;
import java.net.*;

public class ChatController {
    private Socket socket;
    @FXML
    private VBox chatBox;  // Ensure that fx:id="chatBox" matches here

    @FXML
    private TextField sendTextArea;  // Ensure that fx:id="sendTextArea" matches here

    @FXML
    private ScrollPane MessagesChat;  // Ensure that fx:id="MessagesChat" matches here

    @FXML
    public void initialize() {
        // Handle Enter key to send the message
        sendTextArea.setOnAction(event -> onSendButtonClick());
        // Auto-scroll to bottom when new messages appear
        chatBox.heightProperty().addListener((obs, oldVal, newVal) -> {
            MessagesChat.setVvalue(1.0);
        });
        connectToServer("localhost", 12345); // Replace with your friend's IP when deploying
    }
    private void connectToServer(String host, int port) {
        try {
            socket = new Socket(host, port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSendButtonClick() {
        String message = sendTextArea.getText().trim();

        if (!message.isEmpty()) {
            // Create and add the custom message
            MyMessage myMessage = new MyMessage(message);
            chatBox.getChildren().add(myMessage);

            sendTextArea.clear();
            sendTextArea.requestFocus();

            // Scroll after rendering is done
            Platform.runLater(() -> MessagesChat.setVvalue(1.0));
        }
    }

}
