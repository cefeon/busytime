package com.cefeon.busytime;

import com.cefeon.busytime.command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    static Optional<Command> getCommand(String command) {
        return Optional.ofNullable(commandMap.get(command));
    }
}
