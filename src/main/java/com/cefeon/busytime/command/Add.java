package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.JsonError;
import com.cefeon.busytime.JsonResponse;
import com.cefeon.busytime.Log;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static java.nio.file.StandardOpenOption.APPEND;

public class Add implements Command {
    private final Day now = new Day(LocalDateTime.now());
    private final String nowFilename = now.toFileName();
    @Override
    public String execute(String[] args, String listNumbers) {
        Path file = Paths.get(nowFilename + "-" + listNumbers);
        if (args.length == 0) {
            return new JsonError(404,"What task to add? Use: add [task name]").createJSON();
        }
        String taskName = String.join(" ", args);

        try {
            if (!Files.exists(file)) {
                Files.createFile(file);
                return new JsonResponse("Created daily file " + file.getFileName().toString()).createJSON();
            }
        } catch (IOException e) {
            Log.info("Error occurred.");
            e.printStackTrace();
        }

        try {
            String nowTime = new Day(LocalDateTime.now()).toTime();
            String task = nowTime + " " + taskName + "\n";
            Files.write(file, task.getBytes(StandardCharsets.UTF_8), APPEND);
            return new JsonResponse("Added task " + taskName + " at " + nowTime).createJSON();
        } catch (IOException e) {
            return new JsonResponse("Error occurred.").createJSON();
        }
    }
}
