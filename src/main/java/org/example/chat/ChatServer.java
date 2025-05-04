package org.example.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class ChatServer extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ChatServer.class.getResource("ServerChat.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AUUUUUUGram-Server");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        launch();
        connecToServer();
    }
    public static void  connecToServer() throws IOException {

        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Сервер запущено, очікування клієнта...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Клієнт підключився!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));


        while (true){
            String message = in.readLine();
            if (message == null) {
                break;
            }
            System.out.println("Клієнт: " + message);

            System.out.print("Введіть повідомлення для клієнта: ");
            String response = userInput.readLine();
            out.println(response);
        }
        clientSocket.close();
        serverSocket.close();
    }

}