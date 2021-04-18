package com.cefeon.busytime;

import com.cefeon.busytime.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private CommandFactory(){}
    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("add", new Add());
        commandMap.put("help", new Help());
        commandMap.put("sum", new Sum());
        commandMap.put("print", new Print());
        commandMap.put("rm", new Rm());
    }

    static Command getCommand(String command) {
        if (commandMap.containsKey(command)) {
            return commandMap.get(command);
        }
        return new NotFound();
    }
}
