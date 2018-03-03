package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);

    private MealsDaoImpl meals;

    public MealsServlet() {
        super();
        this.meals = new MealsDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to userMeals");

        RequestDispatcher view = request.getRequestDispatcher("/meals.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
