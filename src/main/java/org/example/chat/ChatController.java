package org.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ChatController {
    private String name = "default_test_user";
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    @FXML
    private Label nameLabel;



    @FXML
    private VBox chatBox;

    @FXML
    private TextField sendTextArea;

    @FXML
    private ScrollPane MessagesChat;

    @FXML
    public void initialize() throws IOException {
        setMyName();

        sendTextArea.setOnAction(event -> onSendButtonClick());
        chatBox.heightProperty().addListener((obs, oldVal, newVal) -> MessagesChat.setVvalue(1.0));

        connectToServer("localhost", 12345);
    }

    private void connectToServer(String host, int port) {
        new Thread(() -> {
            try {
                socket = new Socket(host, port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println(name);

                String clientName = in.readLine();
                nameLabel.setText(clientName);
                String line;
                while ((line = in.readLine()) != null) {
                    String finalLine = line;
                    Platform.runLater(() -> {
                        ClientMessage msg = new ClientMessage(finalLine);  // Отправка сообщения от сервера
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
        String message = sendTextArea.getText().trim();

        if (!message.isEmpty()) {
            out.println(message); // Отправка сообщения на сервер

            MyMessage myMessage = new MyMessage(message);
            chatBox.getChildren().add(myMessage);

            sendTextArea.clear();
            sendTextArea.requestFocus();

            Platform.runLater(() -> MessagesChat.setVvalue(1.0));
        }
    }
    public void setMyName() throws IOException {
        System.out.println("Напишете свой никнейм");
        BufferedReader nameInput = new BufferedReader(new InputStreamReader(System.in));
        name = nameInput.readLine();
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
