package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getAllFiltredDateAndTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getAll");
        LocalDateTime startLdt = LocalDateTime.of(startDate, startTime);
        LocalDateTime endLdt = LocalDateTime.of(endDate, endTime);
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id), MealsUtil.DEFAULT_CALORIES_PER_DAY)
                .stream()
                .filter(mealWithExceed-> DateTimeUtil.isBetween(mealWithExceed.getDateTime(), startLdt, endLdt)).collect(Collectors.toList());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.create(meal, AuthorizedUser.id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, AuthorizedUser.id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, AuthorizedUser.id);
        assureIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.id);
    }

}