package org.example.chat;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Сервер запущено, очікування клієнта...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Клієнт підключився!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while ((message = in.readLine()) != null) {
//            ChatController.sendServerMessage();
            System.out.println("Клієнт: " + message);
            System.out.print("Ви: ");
            String response = userInput.readLine();
            out.println(response);
        }

        clientSocket.close();
        serverSocket.close();
    }
}