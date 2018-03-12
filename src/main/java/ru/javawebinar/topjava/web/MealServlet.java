package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealRestController mealRestController;
    private ConfigurableApplicationContext appCtx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealRestController = appCtx.getBean(MealRestController.class);
    }

    @Override
    public void destroy() {
        super.destroy();
        appCtx.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("reset") != null) {
            log.info("reset filtr and getAll");
            request.setAttribute("meals", mealRestController.getAll());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        String strUserID = request.getParameter("userId");
        if (strUserID!=null){
            int userId = Integer.parseInt(strUserID);
            AuthorizedUser.setId(userId);
            request.setAttribute("meals", mealRestController.getAll());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }

        if (request.getParameter("filter") != null) {
            String startDate = request.getParameter("startDate");
            String startTime = request.getParameter("startTime");
            String endDate = request.getParameter("endDate");
            String endTime = request.getParameter("endTime");

            request.setAttribute("meals", mealRestController.getAllFiltredDateAndTime(
                    startDate == null || startDate.isEmpty() ? LocalDate.MIN : LocalDate.parse(startDate),
                    startTime == null || startTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(startTime),
                    endDate == null || endDate.isEmpty() ? LocalDate.MAX : LocalDate.parse(endDate),
                    endTime == null || endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime))
            );
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

        } else {
            String id = request.getParameter("id");
            Meal meal = new Meal(null, id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            if (id.isEmpty()) {
                mealRestController.create(meal);
            } else {
                mealRestController.update(meal, Integer.valueOf(id));
            }
            log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealRestController.delete(id);
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(null, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals", mealRestController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
