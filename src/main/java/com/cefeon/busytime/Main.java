package com.cefeon.busytime;


import com.cefeon.busytime.command.Command;
import spark.Spark;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        if (args.length>=1) {
            try {
                port(Integer.parseInt(args[0]));
            } catch (NumberFormatException e){
                Log.info("This port number is not a number. Using default - 7777");
                port(7777);
            }
        } else {
            port(7777);
        }

        get("/:commandName/", (request, response) -> {
            response.type("application/json");
            String commandName = request.params(":commandName");
            Command command = CommandFactory.getCommand(commandName);
            return command.execute(new String[]{}, "0" );
        });

        Spark.get("/:commandName/:taskName/:listNumber", (request, response) -> {
            response.type("application/json");
            String commandName = request.params(":commandName");
            Command command = CommandFactory.getCommand(commandName);
            String[] params = request.params(":taskName").split(" ");
            String listNumber = request.params(":listNumber");
            return command.execute(params, listNumber);
        });

        Spark.get("/remove/:listNumber", (request, response) -> {
            response.type("application/json");
            String commandName = "remove";
            Command command = CommandFactory.getCommand(commandName);
            String listNumber = request.params(":listNumber");
            String[] params = request.params(":listNumber").split(" ");
            return command.execute(params, listNumber);
        });

        Spark.get("/sum/:date/:taskName", (request, response) -> {
            response.type("application/json");
            String commandName = "sum";
            String date = request.params(":date");
            Command command = CommandFactory.getCommand(commandName);
            String dateName = date + " " + request.params(":taskName");
            String[] params = dateName.split(" ");
            return command.execute(params,"0" );
        });

        Spark.get("/:commandName", (request, response) -> {
            response.redirect(request.pathInfo()+"/");
            return 0;
        });

        Spark.get("/:commandName/:taskName", (request, response) -> {
            response.redirect(request.pathInfo()+"/");
            return 0;
        });
    }
}
