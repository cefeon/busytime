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
    public void execute(String[] args) {
        if (args.length == 1) {
            Log.info("What task to add? \nuse: bt add [task name]");
            return;
        }
        String taskName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        try {
            if (!Files.exists(file)) {
                Files.createFile(file);
                Log.info("\033[0;32m" + "Created daily file\033[0m " + file.getFileName().toString() + "\033[0m");
            }
        } catch (IOException e) {
            Log.info("Error occurred.");
            e.printStackTrace();
        }

        try {
            String task = nowTime + " " + taskName + "\n";
            Files.write(file, task.getBytes(StandardCharsets.UTF_8), APPEND);
            Log.info("\033[0;32mAdded task\033[0m " + taskName + "\033[0;32m at \033[0m" + nowTime);
        } catch (IOException e) {
            Log.info("Error occurred.");
            e.printStackTrace();
        }
    }
}
