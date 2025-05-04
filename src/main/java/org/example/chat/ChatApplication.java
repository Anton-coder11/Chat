package org.example.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("Chat.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AUUUUUUGram");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {

            System.out.println("THIS IS A TEST VERSION \nFOR THIS APPLICATON TO WORK PROPERLY PLEASE RUN ChatServer BEFOREHAND");
            TimeUnit.SECONDS.sleep(1);
        }
        for (int i = 1; i <=3; i++) {
            System.out.println("LEFT TILL LAUNCH -" + i);
            TimeUnit.SECONDS.sleep(1);
        }


        launch();

    }
}