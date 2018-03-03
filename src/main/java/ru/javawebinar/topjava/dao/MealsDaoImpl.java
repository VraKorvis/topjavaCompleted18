package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.ContextListener;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsDaoImpl implements MealsDao {

    private static final Logger log = getLogger(MealsDaoImpl.class);

    @Override
    public void add(Meal meal) {
        log.debug("add " + meal);
        ContextListener.getMealsList().add(meal);
    }

    @Override
    public void update(Meal meal) {
        log.debug("update " + meal);
    }

    @Override
    public void delete(Meal meal) {
        log.debug("delete " + meal);
        ContextListener.getMealsList().remove(meal);
    }

}
