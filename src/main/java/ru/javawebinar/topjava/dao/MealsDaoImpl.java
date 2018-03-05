package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsDaoImpl implements MealsDao {

    private static final Logger log = getLogger(MealsDaoImpl.class);

    private static final AtomicInteger lastId = new AtomicInteger();

    private static final Map<Integer, Meal> meals;

    static {
        meals = new ConcurrentHashMap<>();
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 28, 9, 0), "Завтрак", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 28, 12, 0), "Обед", 1000));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 501));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 29, 11, 0), "Завтрак", 1000));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 29, 14, 0), "Обед", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500));
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public Meal addOrUpdate(Meal meal) {
        log.debug("add/update meal " + meal);
        if (meal.getId() == 0) {
            meal.setId(lastId.incrementAndGet());
        }
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        log.debug("removing meal by id " + meals.remove(id));
    }

    @Override
    public Collection<Meal> getAll() {
        return meals.values();
    }

}
