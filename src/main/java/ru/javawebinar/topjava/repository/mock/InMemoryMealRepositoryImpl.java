package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = getLogger(InMemoryMealRepositoryImpl.class);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values()
                .stream()
                .filter(x -> x.getUserId().equals(userId))
                .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime()))
                .collect(Collectors.toList());
    }

    public List<MealWithExceed> getAllFiltredDateAndTime(int userId, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getWithExceeded(getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY)
                .stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), LocalDateTime.of(startDate, startTime), LocalDateTime.of(endDate, endTime)))
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .collect(Collectors.toList());
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);

        meal.setUserId(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        } else {
            Meal mealRep = repository.get(meal.getId());
            if (mealRep != null && mealRep.getUserId() != userId) {
                return null;
            }
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userId && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);
        if (meal != null && meal.getUserId() == userId) {
            return meal;
        } else return null;
    }

}

