package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private static final Server instance = new Server();

    private Server() {
        threads = new ArrayList<>();
        serverActive = true;
    }

    public static Server getInstance() {
        return instance;
    }

    private static ServerSocket bankServerSocket;
    private ArrayList<ServerThread> threads;
    boolean serverActive;

    @Override
    public void run() {
        try {
            bankServerSocket = new ServerSocket(4244);

            do {
                Socket client = bankServerSocket.accept();
                ServerThread clientThread = new ServerThread(client);
                threads.add(clientThread);
                clientThread.start();
            } while (serverActive);
        } catch (IOException e) {
        } finally {
            try {
                bankServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("server dead");
    }

    @Override
    public void interrupt() {
        serverActive = false;
        for (ServerThread thread : threads) {
            thread.interrupt();
        }
    }
}
