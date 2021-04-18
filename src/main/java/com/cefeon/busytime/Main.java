package com.cefeon.busytime;


import com.cefeon.busytime.command.Command;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        String contentStyle= "<span style=\"font-size: 26px\">";
        port(4567);

        get("/:commandName/", (request, response) -> {
            String commandName = request.params(":commandName");
            Command command = CommandFactory.getCommand(commandName);
            return contentStyle+command.execute(new String[]{});
        });

        get("/:commandName/:taskName/", (request, response) -> {
            String commandName = request.params(":commandName");
            Command command = CommandFactory.getCommand(commandName);
            String[] params = request.params(":taskName").split(" ");
            return contentStyle+command.execute(params);
        });


        get("/:commandName", (request, response) -> {
            response.redirect(request.pathInfo()+"/");
            return 0;
        });

        get("/:commandName/:taskName", (request, response) -> {
            response.redirect(request.pathInfo()+"/");
            return 0;
        });
    }
}
