package command;

import java.util.HashMap;

public class CommandMap {
    HashMap<String, ICommand> commandsMap;
    private static final CommandMap instance = new CommandMap();
    public static CommandMap getInstance() {
        return instance;
    }
    private CommandMap(){
        commandsMap = new HashMap<>();
        commandsMap.put("SIGN_IN", new SignInCommand());
        commandsMap.put("TRANSFER", new TransferCommand());
    }

    public ICommand getCommandsMap(String key) {
        return commandsMap.get(key);
    }
}
