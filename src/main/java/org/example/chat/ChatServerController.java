package org.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServerController {
    private String name = "default_test_server";
    @FXML
    private Label nameLabelServer;
    @FXML
    private VBox chatBox;

    @FXML
    private TextField sendTextAreaServer;

    @FXML
    private ScrollPane MessagesChat;

    private PrintWriter out;
    private BufferedReader in;

    @FXML
    public void initialize() throws IOException {
        setMyName();

        sendTextAreaServer.setOnAction(event -> onSendButtonClick());

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(12345);
                System.out.println("Сервер запущено, очікування клієнта...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клієнт підключився!");

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);


                String clientName = in.readLine();
                Platform.runLater(() -> {
                    nameLabelServer.setText(clientName);
                });

                System.out.println("Клиент подключился с именем: " + clientName);
                out.println(name);
                String line;
                while ((line = in.readLine()) != null) {
                    String finalLine = line;
                    Platform.runLater(() -> {
                        ClientMessage msg = new ClientMessage( finalLine);
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
            // Отправка сообщения на сервер
            out.println(message);

            // Отображение отправленного сообщения в чате
            MyMessage myMessage = new MyMessage(message);
            chatBox.getChildren().add(myMessage);

            sendTextAreaServer.clear();
            sendTextAreaServer.requestFocus();

            // Автопрокрутка
            Platform.runLater(() -> MessagesChat.setVvalue(1.0));
        }
    }
    public void setMyName() throws IOException {
        System.out.println("Напишете свой никнейм");
        BufferedReader nameInput = new BufferedReader(new InputStreamReader(System.in));
        name = nameInput.readLine();
    }

    public String getName() {
        return name;
    }
}
