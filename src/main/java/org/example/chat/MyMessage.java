package org.example.chat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MyMessage extends HBox {
    public MyMessage(String text) {
        // Create a label for the message text
        Label messageLabel = new Label(text);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(300); // Set max width for the message label
        messageLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-background-radius: 10; -fx-alignment: center-right;");

        // Set alignment and padding for the HBox
        this.setAlignment(Pos.CENTER_RIGHT); // Message from the user (aligned to the right)
        this.setPadding(new Insets(5));
        this.getChildren().add(messageLabel);
    }
}
