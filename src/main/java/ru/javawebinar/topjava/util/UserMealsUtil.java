package ru.javawebinar.topjava.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(13, 0), 2000);

        for (UserMealWithExceed x : list) {
            System.out.println(x);
        }
        System.out.println("**********************************************************************************************");
        List<UserMealWithExceed> list2 = getFilteredWithExceededWithMerge(mealList, LocalTime.of(7, 0), LocalTime.of(20, 0), 2000);

        for (UserMealWithExceed x : list2) {
            System.out.println(x);
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate())).values()
                .stream()
                .flatMap(userMeal -> {

                    int sumCalOfUserMeal = userMeal.stream().reduce(0, (sum, meals) -> sum + meals.getCalories(), Integer::sum);
                    boolean isExceed = sumCalOfUserMeal > caloriesPerDay;

                    return userMeal.stream()
                            .filter(timeMeal -> TimeUtil.isBetween(timeMeal.getDateTime().toLocalTime(), startTime, endTime))
                            .map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceed));

                }).collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededWithMerge(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate, Integer> map = new HashMap<>();

        mealList.forEach((userMeal) -> map.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), (cal1, cal2) -> cal1 + cal2));

        mealList.forEach((usMeal) -> {

            boolean isExceed = map.get(usMeal.getDateTime().toLocalDate()) > caloriesPerDay;

            if (TimeUtil.isBetween(usMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                list.add(new UserMealWithExceed(usMeal.getDateTime(), usMeal.getDescription(), usMeal.getCalories(), isExceed));
            }
        });

        return list;
    }

}





