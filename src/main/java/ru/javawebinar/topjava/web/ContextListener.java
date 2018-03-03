package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.slf4j.LoggerFactory.getLogger;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = getLogger(ContextListener.class);

    private static final CopyOnWriteArrayList<Meal> mealsList;

    static {
        mealsList = new CopyOnWriteArrayList<>(Arrays.asList(
                new Meal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),

                new Meal(6, LocalDateTime.of(2015, Month.MAY, 28, 9, 0), "Завтрак", 500),
                new Meal(7, LocalDateTime.of(2015, Month.MAY, 28, 12, 0), "Обед", 1000),
                new Meal(8, LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 501),
                new Meal(9, LocalDateTime.of(2015, Month.MAY, 29, 11, 0), "Завтрак", 1000),
                new Meal(10, LocalDateTime.of(2015, Month.MAY, 29, 14, 0), "Обед", 500),
                new Meal(11, LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500)
        ));
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("load contextListener");
        final ServletContext servletContext = servletContextEvent.getServletContext();

        List<MealWithExceed> mealsListWithExceed = MealsUtil.getFilteredWithExceededInOnePass(mealsList, LocalTime.MIN, LocalTime.MAX, 2000);
        servletContext.setAttribute("mealsList", mealsListWithExceed);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("clear mealsList");
        mealsList.clear();
    }

    public static CopyOnWriteArrayList<Meal> getMealsList() {
        return mealsList;
    }
}
