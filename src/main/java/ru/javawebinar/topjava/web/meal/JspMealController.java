package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping(value = "/delete")
    public String delete(HttpServletRequest request, Model model) {
        int id = getId(request);
        super.delete(id);
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(Objects.requireNonNull(request.getParameter("id")));
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping(value = "/update")
    public String update(HttpServletRequest request, Model model) {
        final Meal meal = get(getId(request));
        model.addAttribute("meal", meal);
        return "mealForm";
    }


    @GetMapping(value = "")
    public String all(HttpServletRequest request) {
        request.setAttribute("meals", getAll());
        return "meals";
    }

    @PostMapping("")
    public String post(HttpServletRequest request) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            create(meal);
        } else {
            update(meal, getId(request));
        }
        return "redirect:/meals";
    }

    @PostMapping(value = "/filter")
    public String filter(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        getBetween(startDate, startTime, endDate, endTime);
        return "meals";
    }
}
