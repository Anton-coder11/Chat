package org.example.chat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ClientMessage extends HBox {
    public ClientMessage(String text) {
        Label messageLabel = new Label(text);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(100);
        messageLabel.setStyle("-fx-background-color: darkblue; -fx-padding: 10; -fx-background-radius: 10; -fx-alignment: center-right;");

        this.setAlignment(Pos.CENTER_RIGHT); // сообщение от пользователя
        this.setPadding(new Insets(5));
        this.getChildren().add(messageLabel);
    }
}
