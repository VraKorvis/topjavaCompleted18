package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Meal {

    private static final AtomicInteger id = new AtomicInteger();

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        id.incrementAndGet();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
