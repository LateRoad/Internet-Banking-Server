package logic.request;

import java.util.HashMap;

public class CommandMap {
    private HashMap<String, ICommand> commandsMap;
    private static final CommandMap instance = new CommandMap();

    public static CommandMap getInstance() {
        return instance;
    }

    private CommandMap() {
        commandsMap = new HashMap<>();
        commandsMap.put("SIGN_IN", new SignInCommand());
        commandsMap.put("TRANSFER", new TransferCommand());
        commandsMap.put("PAYMENT", new PaymentCommand());
    }

    public ICommand getCommandsMap(String key) {
        return commandsMap.get(key);
    }
}
