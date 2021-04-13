package com.cefeon.busytime;

import com.cefeon.busytime.command.Command;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            Log.info("Command required \nuse: bt help for list of commands");
            return;
        }
        String commandName = args[0];
        if (Boolean.TRUE.equals(CommandFactory.commandExist(commandName))) {
            Command targetCommand = CommandFactory.getCommand(commandName);
            targetCommand.execute(args);
        } else {
            Log.info("Command doesn't exist");
        }
    }
}
