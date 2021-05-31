package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.JsonError;
import com.cefeon.busytime.JsonResponse;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Sum implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return new JsonError(404, "What day to count? Use: /sum/[date]/[taskname] example: /sum/21-04-2000/task").toGson();
        }

        if (args.length == 1) {
            return new JsonError(404, "What task to count? Use: /sum/[date]/[taskname] example: /sum/21-04-2000/task").toGson();
        }

        try {
            String taskName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            int duration = getTaskDuration(new Day(args[0]), taskName);
            JsonResponse jr = new JsonResponse("Total " + taskName + " duration");
            jr.addData(duration + " minutes");
            return jr.toGson();
        } catch (DateTimeParseException e) {
            return new JsonError(404, "Date format wrong. Use: dd-mm-yyyy. Example: 21-04-2000").toGson();
        } catch (IOException e) {
            return new JsonError(404,"There is no log for that date").toGson();
        }
    }

    private int getTaskDuration(Day date, String taskName) throws IOException {
        List<Task> tasks = Task.listFromFile(date);
        return tasks.stream().filter(task -> task.getName().equals(taskName)).mapToInt(Task::getDuration).sum();
    }
}
