package com.cefeon.busytime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {
    private final String name;
    private final String startTime;
    private int duration;

    public Task(String name, String time) {
        this.name = name;
        this.startTime = time;
        this.duration = 0;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    private int getMinutes(){
        int hours = Integer.parseInt(startTime.split(":")[0]);
        return Integer.parseInt(startTime.split(":")[1]) + hours * 60;
    }

    public static List<Task> listFromFile(Day date) throws IOException {
        String filePath = date.toFileName();
        Path path = Paths.get(filePath);
        try(Stream<String> stream = Files.lines(path)) {
            List<String> lines = stream.collect(Collectors.toList());
            List<Task> tasks = new ArrayList<>();
            lines.forEach(x -> tasks.add(new Task(x.split(" ", 2)[1], x.split(" ", 2)[0])));
            setTasksDuration(tasks);
            return tasks;
        }
    }

    private static List<Task> setTasksDuration(List<Task> tasks) {
        for (int i = 0; i <= tasks.size() - 2; i++) {
            int minutesOfDayCurrent = tasks.get(i).getMinutes();
            int minutesOfDayNext = tasks.get(i + 1).getMinutes();
            tasks.get(i).setDuration(minutesOfDayNext - minutesOfDayCurrent);
        }
        return tasks;
    }

    @Override
    public String toString() {
        return name + " " + startTime;
    }
}
