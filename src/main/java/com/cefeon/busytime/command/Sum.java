package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Sum implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "What day to count? <br />use: /sum/[date]/[taskname]<br />example: /sum/21-04-2000/task";
        }

        if (args.length == 1) {
            return "What task to count? <br />use: /sum/[date]/[taskname]<br />example: /sum/21-04-2000/task";
        }

        try {
            String taskName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            int duration = getTaskDuration(new Day(args[0]), taskName);
            return "<span style=\"color: #36d036\">Total </span>" + taskName + "<span style=\"color: #36d036\"> duration: </span>" + duration + " minutes";
        } catch (DateTimeParseException e) {
            return "Date format wrong.<br />use: dd-mm-yyyy<br />example: 21-04-2000";
        } catch (IOException e) {
            return "There is no log for that date";
        }
    }

    private int getTaskDuration(Day date, String taskName) throws IOException {
        List<Task> tasks = Task.listFromFile(date);
        return tasks.stream().filter(task -> task.getName().equals(taskName)).mapToInt(Task::getDuration).sum();
    }
}
