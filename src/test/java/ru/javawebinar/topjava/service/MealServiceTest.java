package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/initDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(100002, ADMIN_ID);
        assertMatch(meal, ADMIN_LANCH);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100003, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), LIST_MEALS_BY_ADMIN_AFTER_DELETE);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> actual = service.getBetweenDates(LocalDate.of(2018, Month.JUNE, 1), LocalDate.of(2018, Month.JUNE, 30), USER_ID);
        assertMatch(actual, LIST_BETWEEN_DATE);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(ADMIN_ID), LIST_MEALS_BY_ADMIN);
    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(USER_LANCH);
        meal.setId(USER_LANCH.getId());
        meal.setCalories(3000);
        meal.setDescription("Ланч");
        service.update(meal, USER_ID);
        assertMatch(service.get(USER_LANCH.getId(), USER_ID), meal);
    }

    @Test
    public void create() throws Exception {
        Meal meal = new Meal(LocalDateTime.of(2018, Month.JUNE, 14, 17, 0), "Праздничный ужин", 5000);
        service.create(meal, USER_ID);
        assertMatch(service.get(100008, USER_ID), meal);
    }

    @Test(expected = NotFoundException.class)
    public void getForeign() throws Exception {
       service.get(100002, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeign() throws Exception {
        service.delete(1000002, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateForeign() throws Exception {
        Meal meal = new Meal(USER_LANCH);
        meal.setId(USER_LANCH.getId());
        meal.setCalories(1111);
        meal.setDescription("Ланч не админа");
        service.update(meal, ADMIN_ID);
    }

    @Test(expected = DuplicateKeyException.class)
    public void createDuplicateDateTimeForOneUser(){
        Meal meal = new Meal(USER_LANCH);
        meal.setId(null);
        service.create(meal, USER_ID);
    }

}