module org.example.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.chat to javafx.fxml;
    exports org.example.chat;


}