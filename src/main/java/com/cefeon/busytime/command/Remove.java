package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
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
    private final Path file = Paths.get(nowFilename);

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            try{
                removeLastLine(file);
                return "<span style=\"color: #36d036\">Removed last task</span> ";
            } catch (IOException e) {
                return "Error occurred.";
            }
        }

        try{
            String taskName = String.join(" ", Arrays.copyOfRange(args, 0, args.length));
            removeTaskByName(file, taskName);
            return "<span style=\"color: #36d036\">Removed all tasks named</span> " + taskName;
        } catch (IOException e) {
            return "Error occurred.";
        }
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
