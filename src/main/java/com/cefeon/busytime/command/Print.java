package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Log;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Print implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            Log.info("What day to print? \nuse: bt print [date]\nexample: bt print 21-04-2000");
            return;
        }

        try {
            Day date = new Day(args[1]);
            print(date);
        } catch (DateTimeParseException e) {
            Log.info("Date format wrong.\nuse: dd-mm-yyyy\nexample: 21-04-2000");
        } catch (IOException e) {
            Log.info("There is no log for that date");
        }
    }

    private void print(Day date) throws IOException {
        Log.info("Tasks for " + date.toDate());
        Task.listFromFile(date).forEach(x->Log.info(x.toString()));
    }
}
