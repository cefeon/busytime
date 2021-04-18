package com.cefeon.busytime.command;

import com.cefeon.busytime.Day;
import com.cefeon.busytime.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Print implements Command {
    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return "What day to print? <br />use: /print/[date]<br />example: /print/21-04-2000";
        }

        try {
            Day date = new Day(args[0]);
            return print(date);
        } catch (DateTimeParseException e) {
            return "Date format wrong.<br />use: dd-mm-yyyy<br />example: 21-04-2000";
        } catch (IOException e) {
            return "There is no log for that date";
        }
    }

    private String print(Day date) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("<b>Tasks for ").append(date.toDate()).append("</b><br />");
        Task.listFromFile(date).forEach(x-> builder.append(x).append("<br />"));
        return builder.toString();
    }
}
