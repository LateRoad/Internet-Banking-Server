package logic.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private static Server instance = null;

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
            threads = new ArrayList<>();
        }
        return instance;
    }

    private static ServerSocket bankServerSocket;
    private static ArrayList<ServerThread> threads;
    public boolean serverActive = true;

    private Server() {
    }

    @Override
    public void run() {
        try {
            bankServerSocket = new ServerSocket(4244);

            while (serverActive) {
                Socket client = bankServerSocket.accept();
                ServerThread clientThread = new ServerThread(client);
                threads.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
        } finally {
            try {
                bankServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void closeThreads() {
        instance = null;
        serverActive = false;
        for (ServerThread thread : threads) {
            thread.isAlive = false;
        }
    }
}
