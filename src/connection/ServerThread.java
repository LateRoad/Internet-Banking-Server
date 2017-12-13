package connection;

import command.CommandManager;
import security.HashMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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
        int i = 0;
        try {
            serverReader = new ObjectInputStream(client.getInputStream());
            while (isAlive) {
                if(i == 0){
                    message = (String) serverReader.readObject();
                    message = HashMessage.getInstance().decodeWithKey(message.getBytes(StandardCharsets.UTF_8), new Date().toString());
                    System.out.println(message);
                    if ("EXIT".equals(message)) {
                        break;
                    }
                    i++;
                }
                else{
                    message = (String) serverReader.readObject();
                    message = HashMessage.getInstance().decode(message.getBytes(StandardCharsets.UTF_8), socketName);
                    System.out.println(message);
                    if ("EXIT".equals(message)) {
                        break;
                    }
                }

                answer = commandManager.execute(message);
                if(answer.split("%21")[0].equals("SIGNUP")){
                    socketName = answer.split("%21")[1].split("\"")[3];
                    HashMessage.setKey(socketName, new Date().toString());
                }
                answer = new String (HashMessage.getInstance().encode(answer, socketName), "UTF-8");
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
