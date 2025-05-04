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


    }
    public static void  connectToServer() throws IOException {

    }

}