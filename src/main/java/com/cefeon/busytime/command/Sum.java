package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Log;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Sum implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            Log.error("What day to count? \nuse: bt sum [date] [taskname]\nexample: bt sum 21-04-2000");
            return;
        }

        if (args.length == 2) {
            Log.error("What task to count? \nuse: bt sum [date] [taskname]\nexample: bt sum 21-04-2000");
            return;
        }

        try {
            String taskName = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
            int duration = getTaskDuration(new Day(args[1]), taskName);
            Log.info("\033[0;32mTotal \033[0m" + taskName + " \033[0;32mduration: \033[0m" + duration + " minutes");
        } catch (DateTimeParseException e) {
            Log.error("Date format wrong.\nuse: dd-mm-yyyy\nexample: 21-04-2000");
        } catch (IOException e) {
            Log.error("There is no log for that date");
        }
    }

    private int getTaskDuration(Day date, String taskName) throws IOException {
        List<Task> tasks = Task.listFromFile(date);
        return tasks.stream().filter(task -> task.getName().equals(taskName)).mapToInt(Task::getDuration).sum();
    }
}
