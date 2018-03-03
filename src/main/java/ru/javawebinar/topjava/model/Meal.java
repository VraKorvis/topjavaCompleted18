package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Meal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private static List<Meal> mealList;

    static {
         mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),

                new Meal(LocalDateTime.of(2015, Month.MAY, 28, 9, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 28, 12, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 501),
                new Meal(LocalDateTime.of(2015, Month.MAY, 29, 11, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 29, 14, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500)
        );
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
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

    public static List<Meal> getMealList() {
        return mealList;
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
