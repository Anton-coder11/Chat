package org.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerController {

    @FXML
    private VBox chatBox;

    @FXML
    private TextField sendTextAreaServer;

    @FXML
    private ScrollPane MessagesChat;

    private PrintWriter out;
    private BufferedReader in;
    @FXML
    public void initialize() {
        sendTextAreaServer.setOnAction(event -> onSendButtonClick());
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                Socket clientSocket = serverSocket.accept();

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String line;
                while ((line = in.readLine()) != null) {
                    String finalLine = line;
                    Platform.runLater(() -> {
                        MyMessage msg = new MyMessage("Клиент: " + finalLine);
                        chatBox.getChildren().add(msg);
                        MessagesChat.setVvalue(1.0);
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    protected void onSendButtonClick() {
        String message = sendTextAreaServer.getText().trim();

        if (!message.isEmpty()) {
            // Create and add the custom message
            MyMessage myMessage = new MyMessage(message);
            chatBox.getChildren().add(myMessage);

            sendTextAreaServer.clear();
            sendTextAreaServer.requestFocus();

            // Scroll after rendering is done
            Platform.runLater(() -> MessagesChat.setVvalue(1.0));
        }
    }
}
