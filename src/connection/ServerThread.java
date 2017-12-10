package connection;

import command.CommandManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    private ObjectInputStream serverReader;
    private ObjectOutputStream serverWriter;
    private Socket client;

    public ServerThread(Socket client) throws IOException {
        this.client = client;
        serverWriter = new ObjectOutputStream(client.getOutputStream());
        serverReader = null;
    }

    @Override
    public void run() {
        String message;
        String answer;
        CommandManager commandManager = new CommandManager();
        try {
            serverReader = new ObjectInputStream(client.getInputStream());
            while (true) {
                message = (String) serverReader.readObject();
                if("EXIT".equals(message)){
                    System.out.println("aaaaa");
                    break;
                }
                answer = commandManager.execute(message);
                serverWriter.writeObject(answer);
                serverWriter.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
