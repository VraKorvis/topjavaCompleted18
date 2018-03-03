package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class MealsDataBase {

    private static final CopyOnWriteArraySet<Meal> meals;

    static {
        meals = new CopyOnWriteArraySet<>(Arrays.asList(
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
        ));
    }

    private MealsDataBase() {
    }

    public static CopyOnWriteArraySet<Meal> getMeals() {
        return meals;
    }
}
