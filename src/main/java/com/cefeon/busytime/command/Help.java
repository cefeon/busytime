package com.cefeon.busytime.command;

import com.cefeon.busytime.Log;

import java.util.HashMap;
import java.util.Map;

public class Help implements Command {
    final Map<String, String> helpMap = new HashMap<>();

    @Override
    public void execute(String[] args) {
        helpMap.put("add [task name]", "add new task");
        helpMap.put("sum", "sum time for selected day and task");
        helpMap.put("print", "print tasks for selected day");
        helpMap.put("help", "print list of all commands");
        helpMap.forEach((x, y) -> Log.INFO(x + " -> " + y));
    }
}
