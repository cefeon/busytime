package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.JsonError;
import com.cefeon.busytime.JsonResponse;
import com.cefeon.busytime.Task;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Print implements Command {

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return new JsonError(404, "What day to print? use: /print/[date] example: /print/21-04-2000").createJSON();
        }

        try {
            Day date = new Day(args[0]);
            return print(date);
        } catch (DateTimeParseException e) {
            return new JsonError(404, "Date format wrong. Use: dd-mm-yyyy. example: 21-04-2000").createJSON();
        } catch (IOException e) {
            return  new JsonError(404, "There is no log for that date").createJSON();
        }
    }

    private String print(Day date) throws IOException {
        JsonResponse jr = new JsonResponse("Tasks done " + date.toDate());
        Task.listFromFile(date).forEach(jr::addData);
        return jr.createJSON();
    }
}
