package command;

public class CommandManager {
    private CommandMap commandMap = CommandMap.getInstance();


    public String execute(String message){
        String[] command = message.split("%21");
        return commandMap.getCommandsMap(command[0]).execute(command[1]);
    }
}
