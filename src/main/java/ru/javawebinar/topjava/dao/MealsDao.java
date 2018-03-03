package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

public interface MealsDao {

    void add(Meal meal);
    void update(Meal meal);
    void delete(Meal meal);

}
