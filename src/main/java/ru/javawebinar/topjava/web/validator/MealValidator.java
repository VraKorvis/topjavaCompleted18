package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Component
public class MealValidator implements Validator {

    @Autowired
    MealService mealService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Meal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Meal meal = (Meal) target;
        List<Meal> meals = mealService.getBetweenDateTimes(meal.getDateTime(), meal.getDateTime(), AuthorizedUser.id());
        meals.forEach(existedMeal -> {
            if(!existedMeal.getId().equals(meal.getId()))
                errors.rejectValue("dateTime", "meal.exists", "Meal with this dateTime already exists");
        });
    }
}