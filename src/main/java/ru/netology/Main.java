package ru.netology;

import ru.netology.server.CalculateMax;
import ru.netology.server.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static final int PORT = 8989;

    public static void main(String[] args) throws FileNotFoundException {
        Log log = new Log();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    out.println("Создано подключение на сервере через порт " + clientSocket.getLocalPort());
                    log.addLog(in.readLine());
                    CalculateMax calculateMax = new CalculateMax(log);
                    out.println(calculateMax.calcMax());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}