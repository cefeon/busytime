package com.cefeon.busytime.command;

public class NotFound implements Command {
    @Override
    public String execute(String[] args) {
        return "There is no such command. <br />use: /help to get help ";
    }
}
