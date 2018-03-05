package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealsDao {

    Meal get(int id);
    Meal addOrUpdate(Meal meal);
    void delete(int id);
    Collection<Meal> getAll();

}
