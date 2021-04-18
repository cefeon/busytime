package com.cefeon.busytime.command;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Help implements Command {
    final Map<String, String> helpMap = new HashMap<>();

    @Override
    public String execute(String[] args) {
        helpMap.put("add/[task name]", "add new task");
        helpMap.put("sum", "sum time for selected day and task");
        helpMap.put("print", "print tasks for selected day");
        helpMap.put("help", "print list of all commands");
        helpMap.put("remove", "remove last added task");
        return helpMap.keySet().stream()
                .map(key -> "<b>" + key + ": </b>" + helpMap.get(key))
                .collect(Collectors.joining("<br /> ", "", ""));
    }
}
