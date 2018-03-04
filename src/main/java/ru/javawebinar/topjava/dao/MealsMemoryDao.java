package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsMemoryDao implements MealsDao {

    private static final Logger log = getLogger(MealsMemoryDao.class);

    private ConcurrentHashMap<Integer, Meal> meals;

    public MealsMemoryDao() {
        this.meals = MealsDataBase.getMeals();
    }

    public ConcurrentHashMap<Integer, Meal> getMeals() {
        return meals;
    }

    @Override
    public void addOrUpdate(Meal meal) {
        log.debug("add/update meal " + meal);
        if (meal.getId() == 0) {
            meal.setId(MealsDataBase.getLastId().getAndIncrement());
        }
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        log.debug("removing meal by id " + id);
        meals.remove(id);
    }

}
