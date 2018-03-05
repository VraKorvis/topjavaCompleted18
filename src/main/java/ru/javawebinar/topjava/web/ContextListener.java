package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import ru.javawebinar.topjava.dao.MealsDaoImpl;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalTime;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger log = getLogger(ContextListener.class);

    private MealsDaoImpl mealsDao;

    public ContextListener() {
        this.mealsDao = new MealsDaoImpl();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.debug("load contextListener");
        final ServletContext servletContext = servletContextEvent.getServletContext();

        List<MealWithExceed> mealsListWithExceed = MealsUtil.getFilteredWithExceededInOnePass(mealsDao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        servletContext.setAttribute("mealsList", mealsListWithExceed);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("clear meals");
        mealsDao.getAll().clear();
    }

}
