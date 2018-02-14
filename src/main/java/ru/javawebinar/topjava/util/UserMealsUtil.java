package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),

                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 9, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 12, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 28, 20, 0), "Ужин", 501),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 11, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 14, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 500)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed x : list) {
            System.out.println(x);
        }

        System.out.println("************************");

        List<UserMealWithExceed> list2 = getFilteredWithExceededWithMerge(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed x : list2) {
            System.out.println(x);
        }

    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate())).values()
                .stream()
                .flatMap(userMealList -> {

                    int sumCalOfUserMeal = userMealList.stream().mapToInt(UserMeal::getCalories).sum();
                    boolean isExceed = sumCalOfUserMeal > caloriesPerDay;

                    return userMealList.stream()
                            .filter(timeMeal -> TimeUtil.isBetween(timeMeal.getDateTime().toLocalTime(), startTime, endTime))
                            .map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceed));

                }).collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededWithMerge(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> userMealWithExceed = new ArrayList<>();
        Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();

        mealList.forEach((userMeal) -> caloriesSumByDate.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum));

        mealList.forEach((userMeal) -> {

            boolean isExceed = caloriesSumByDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay;

            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExceed.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), isExceed));
            }
        });

        return userMealWithExceed;
    }

}





