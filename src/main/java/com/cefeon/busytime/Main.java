package com.cefeon.busytime;

import com.cefeon.busytime.command.Command;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            Log.info("Command required \nuse: bt help for list of commands");
            return;
        }

        String commandName = args[0];

        Optional<Command> command = CommandFactory.getCommand(commandName);
        if (command.isPresent()) {
            Command targetCommand = command.get();
            targetCommand.execute(args);
        } else {
            Log.info("Command doesn't exist");
        }
    }
}
