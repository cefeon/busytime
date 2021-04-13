package com.cefeon.busytime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Day {

    private final LocalDateTime date;

    public Day(LocalDateTime date) {
        this.date = date;
    }

    public Day(LocalDate date) {
        this.date = date.atTime(0, 0);
    }

    public Day(String date) throws DateTimeParseException {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFromParam = date.equals("today") ? LocalDate.now() : LocalDate.parse(date, dateFormat);
        this.date = dateFromParam.atTime(0, 0);
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
