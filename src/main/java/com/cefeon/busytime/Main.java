package com.cefeon.busytime;


import com.cefeon.busytime.command.Command;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        String contentStyle= "<span style=\"font-size: 26px\">";
        if (args.length>=1) {
            port(Integer.parseInt(args[0]));
        } else {
            port(7777);
        }

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

        get("/sum/:date/:taskName", (request, response) -> {
            String commandName = "sum";
            String date = request.params(":date");
            Command command = CommandFactory.getCommand(commandName);
            String dateName = date + " " + request.params(":taskName");
            String[] params = dateName.split(" ");
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
