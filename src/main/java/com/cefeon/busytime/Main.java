package com.cefeon.busytime;


import com.cefeon.busytime.command.Command;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        String contentStyle= "<span style=\"font-size: 26px\">";
        get("/add/:taskName", (request, response) -> {
            String commandName = "add";
            Command command = CommandFactory.getCommand(commandName);
            if (!request.params(":taskname").isEmpty()) {
                String[] params = request.params(":taskName").split(" ");
                return contentStyle+command.execute(params);
            } else {
                return contentStyle+command.execute(new String[]{});
            }
        });

        get("/add/", (request, response) -> {
            String commandName = "add";
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(new String[]{});
        });

        get("/help/", (request, response) -> {
            String commandName = "help";
            String[] params = {""};
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(params);
        });

        get("/remove/", (request, response) -> {
            String commandName = "rm";
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(new String[]{});
        });

        get("/remove/:taskName", (request, response) -> {
            String commandName = "rm";
            String[] params = request.params(":taskName").split(" ");
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(params);
        });

        get("/print/", (request, response) -> {
            String commandName = "print";
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(new String[]{});
        });

        get("/print/:date", (request, response) -> {
            String commandName = "print";
            String[] params = request.params(":date").split(" ");
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(params);
        });

        get("/sum/:date/:task", (request, response) -> {
            String commandName = "sum";
            String date = request.params(":date");
            String task = request.params(":task");
            Command command = CommandFactory.getCommand(commandName);
            String[] params = {date, task};
            return contentStyle+command.execute(params);
        });
        get("/sum/:date/", (request, response) -> {
            String commandName = "sum";
            String date = request.params(":date");
            Command command = CommandFactory.getCommand(commandName);
            String[] params = {date};
            return contentStyle+command.execute(params);
        });
    }
}
