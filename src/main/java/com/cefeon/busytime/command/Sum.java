package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Log;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Sum implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            Log.INFO("What day to count? \nuse: bt sum [date] [taskname]\nexample: bt sum 21-04-2000");
            return;
        }

        if (args.length == 2) {
            Log.INFO("What task to count? \nuse: bt sum [date] [taskname]\nexample: bt sum 21-04-2000");
            return;
        }

        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate dateFromParam = args[1].equals("today") ? LocalDate.now() : LocalDate.parse(args[1], dateFormat);
            String taskName = args[2];
            Day date = new Day(dateFromParam.atTime(0, 0));
            List<Task> tasks = Task.listFromFile(date);
            Task.setTasksDuration(tasks);
            Log.INFO("\033[0;32mTotal \033[0m" + taskName + " \033[0;32mduration: \033[0m" + getTaskDuration(tasks, taskName) + " minutes");
        } catch (DateTimeParseException e) {
            Log.INFO("Date format wrong.\nuse: dd-mm-yyyy\nexample: 21-04-2000");
        } catch (IOException e) {
            Log.INFO("There is no log for that date");
        }
    }

    private int getTaskDuration(List<Task> tasks, String taskName) {
        return tasks.stream().filter(task -> task.getName().equals(taskName)).mapToInt(Task::getDuration).sum();
    }
}
