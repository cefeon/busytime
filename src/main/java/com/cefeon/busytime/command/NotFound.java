package com.cefeon.busytime.command;

import com.cefeon.busytime.JsonError;

public class NotFound implements Command {
    @Override
    public String execute(String[] args) {
        return new JsonError(404, "There is no such command. use: /help to get help ").toGson();
    }
}
