package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Log;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Sum implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            Log.ERROR("What day to count? \nuse: bt sum [date] [taskname]\nexample: bt sum 21-04-2000");
            return;
        }

        if (args.length == 2) {
            Log.ERROR("What task to count? \nuse: bt sum [date] [taskname]\nexample: bt sum 21-04-2000");
            return;
        }

        try {
            String taskName = args[2];
            Day date = new Day(args[1]);
            List<Task> tasks = Task.listFromFile(date);
            Log.INFO("\033[0;32mTotal \033[0m" + taskName + " \033[0;32mduration: \033[0m" + getTaskDuration(tasks, taskName) + " minutes");
        } catch (DateTimeParseException e) {
            Log.ERROR("Date format wrong.\nuse: dd-mm-yyyy\nexample: 21-04-2000");
        } catch (IOException e) {
            Log.ERROR("There is no log for that date");
        }
    }

    private int getTaskDuration(List<Task> tasks, String taskName) {
        return tasks.stream().filter(task -> task.getName().equals(taskName)).mapToInt(Task::getDuration).sum();
    }
}
