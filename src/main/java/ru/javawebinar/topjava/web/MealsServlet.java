package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    private static final String LIST_MEALS = "/meals.jsp";
    private static final String EDIT_OR_ADD = "/edit.jsp";

    private MealsDaoImpl mealsDao;

    public MealsServlet() {
        super();
        this.mealsDao = new MealsDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");

        String forward;
        final String action = request.getParameter("action");

        if (action == null) {
            forward = LIST_MEALS;
            setAttrListOfMeals(request);
            request.getRequestDispatcher(forward).forward(request, response);

        } else {
            switch (action.toLowerCase()) {
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    mealsDao.delete(id);
                    break;
                case "edit":
                    forward = EDIT_OR_ADD;
                    id = Integer.parseInt(request.getParameter("id"));
                    Meal meal = mealsDao.getMeals().get(id);
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher(forward).forward(request, response);
                    break;
                case "add":
                    forward = EDIT_OR_ADD;
                    request.getRequestDispatcher(forward).forward(request, response);
                    break;
                case "listMeals":
                    forward = LIST_MEALS;
                    setAttrListOfMeals(request);
                    request.getRequestDispatcher(forward).forward(request, response);
                    break;
            }
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final String description = request.getParameter("description");
        final int calories = Integer.parseInt(request.getParameter("calories"));

        final String dateAndTime = request.getParameter("date") + " " + request.getParameter("time");
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        final LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, formatter);

        Meal meal = new Meal(0, dateTime, description, calories);
        final String id = request.getParameter("id");
        if (!id.equals("")) {
            meal.setId(Integer.parseInt(id));
        }
        mealsDao.addOrUpdate(meal);

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        setAttrListOfMeals(request);
        view.forward(request, response);
    }

    private void setAttrListOfMeals(HttpServletRequest request) {
        List<MealWithExceed> mealsListWithExceed = MealsUtil.getFilteredWithExceededInOnePass(mealsDao.getMeals().values(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("mealsList", mealsListWithExceed);
    }
}
