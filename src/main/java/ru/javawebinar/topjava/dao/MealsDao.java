package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealsDao {

    Meal getId(int id);
    void addOrUpdate(Meal meal);
    void delete(int id);
    Collection<Meal> getAll();

}
