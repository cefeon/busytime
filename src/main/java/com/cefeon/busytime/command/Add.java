package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Log;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

import static java.nio.file.StandardOpenOption.APPEND;

public class Add implements Command {
    private final Day now = new Day(LocalDateTime.now());
    private final String nowFilename = now.toFileName();
    private final String nowTime = now.toTime();
    private final Path file = Paths.get(nowFilename);

    @Override
    public String execute(String[] args) {
        StringBuilder builder = new StringBuilder();
        if (args.length == 0) {
            return "What task to add? \nuse: bt add [task name]";
        }
        String taskName = String.join(" ", Arrays.copyOfRange(args, 0, args.length));

        try {
            if (!Files.exists(file)) {
                Files.createFile(file);
                builder.append("<span style=\"color: #36d036\">" + "Created daily file </span> " + file.getFileName().toString() + "<br>");
            }
        } catch (IOException e) {
            Log.info("Error occurred.");
            e.printStackTrace();
        }

        try {
            String task = nowTime + " " + taskName + "\n";
            Files.write(file, task.getBytes(StandardCharsets.UTF_8), APPEND);
            builder.append("<span style=\"color: #36d036\">Added task </span> " + taskName + "<span style=\"color: #36d036\"> at </span> " + nowTime);
            return builder.toString();
        } catch (IOException e) {
            return "Error occurred.";
        }
    }
}
