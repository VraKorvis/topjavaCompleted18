package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

public interface MealsDao {

    void addOrUpdate(Meal meal);
    void delete(int id);

}
