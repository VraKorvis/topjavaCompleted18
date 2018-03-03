package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to userMeals");

        List<MealWithExceed> mealsList = MealsUtil.getFilteredWithExceededInOnePass(Meal.getMealList(), LocalTime.MIN, LocalTime.MAX, 2000);

        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        request.setAttribute("mealsList", mealsList);
        view.forward(request, response);

    }
}
