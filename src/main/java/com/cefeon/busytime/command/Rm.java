package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Log;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rm implements Command {
    private final Day now = new Day(LocalDateTime.now());
    private final String nowFilename = now.toFileName();
    private final Path file = Paths.get(nowFilename);

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            try{
                removeLastLine(file);

                Log.info("\033[0;32mRemoved last task\033[0m ");
            } catch (IOException e) {
                Log.info("Error occurred.");
            }
        }

        if (args.length >= 2) {
            try{
                String taskName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                removeTaskByName(file, taskName);
                Log.info("\033[0;32mRemoved all tasks named\033[0m " + taskName);
            } catch (IOException e) {
                Log.info("Error occurred.");
            }
        }
    }

    private void removeTaskByName(Path file, String taskname) throws IOException {
        List<String> lines = Files.lines(file)
                .filter(x->!(x.matches(".*?"+taskname+"$")))
                .collect(Collectors.toList());
        Files.write(file,lines);
    }

    private void removeLastLine(Path file) throws IOException {
        List<String> lines = Files.lines(file)
                .collect(Collectors.toList());
        lines.remove(lines.size() - 1);
        Files.write(file,lines);
    }
}
