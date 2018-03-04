package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class MealsDataBase {

    private static final ConcurrentHashMap<Integer, Meal> meals;

    private static final AtomicInteger lastId = new AtomicInteger();

    static {
        meals = new ConcurrentHashMap<>();
        int id = lastId.getAndIncrement();
        Meal meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 28, 9, 0), "Завтрак", 500);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 28, 12, 0), "Обед", 1000);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 501);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 29, 11, 0), "Завтрак", 1000);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 29, 14, 0), "Обед", 500);
        meals.put(id, meal);
        id = lastId.getAndIncrement();
        meal = new Meal(id, LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500);
        meals.put(id, meal);
    }

    private MealsDataBase() {
    }

    static ConcurrentHashMap<Integer, Meal> getMeals() {
        return meals;
    }

    static AtomicInteger getLastId() {
        return lastId;
    }
}
