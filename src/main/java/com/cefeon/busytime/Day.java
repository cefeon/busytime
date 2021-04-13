package com.cefeon.busytime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Day {

    private final LocalDateTime date;

    public Day(LocalDateTime date) {
        this.date = date;
    }

    public String toFileName() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dMMMMy");
        return formatter.format(date) + ".txt";
    }

    public String toDate() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return formatter.format(date);
    }

    public String toTime() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(date);
    }
}
