package com.cefeon.busytime;

import com.cefeon.busytime.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("add", new Add());
        commandMap.put("help", new Help());
        commandMap.put("sum", new Sum());
        commandMap.put("print", new Print());
    }

    static Boolean commandExist(String command) {
        return commandMap.containsKey(command);
    }

    static Command getCommand(String command) {
        return commandMap.get(command);
    }
}
