package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.JsonError;
import com.cefeon.busytime.JsonResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Remove implements Command {
    private final Day now = new Day(LocalDateTime.now());
    private final String nowFilename = now.toFileName();

    @Override
    public String execute(String[] args, String listNumbers) {
        Path file = Paths.get(nowFilename + "-" + listNumbers);
        if (args.length != 0) {
            try{
                removeLastLine(file);
                return new JsonResponse("Removed last task from list " + listNumbers).createJSON();
            } catch (IOException e) {
                return new JsonError(500, "Error occurred.").createJSON();
            }
        } else return new JsonError(404, "List Number not delivered.").createJSON();

        /*try{
            String taskName = String.join(" ", args);
            removeTaskByName(file, taskName);
            return new JsonResponse("Removed all tasks named " + taskName).createJSON();
        } catch (IOException e) {
            return new JsonError(500, "Error occurred.").createJSON();
        }*/
    }

    private void removeTaskByName(Path file, String taskName) throws IOException {
        try (Stream<String> stream = Files.lines(file)){
            Deque<String> lines = stream
                    .filter(x -> !(x.matches(".*?" + taskName + "$")))
                    .collect(Collectors.toCollection(ArrayDeque::new));
            Files.write(file, lines);
        }
    }

    private void removeLastLine(Path file) throws IOException {
        try (Stream<String> stream = Files.lines(file)) {
            Deque<String> lines = stream.collect(Collectors.toCollection(ArrayDeque::new));
            lines.pollLast();
            Files.write(file, lines);
        }
    }
}
