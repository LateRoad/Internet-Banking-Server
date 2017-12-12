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
    volatile boolean isAlive = true;
    private String socketName;

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
            while (isAlive) {
                message = (String) serverReader.readObject();
                if ("EXIT".equals(message)) {
                    break;
                }
                answer = commandManager.execute(message);
                if(answer.split("%21")[0].equals("SIGNUP")){
                    socketName = answer.split("%21")[1].split("\"")[3];
                }
                serverWriter.writeObject(answer);
                serverWriter.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }
}
