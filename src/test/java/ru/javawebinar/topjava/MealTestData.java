package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final Meal ADMIN_LANCH = new Meal(100002, LocalDateTime.of(2018, Month.MARCH, 12, 14, 0), "Админ ланч", 510);
    public static final Meal USER_LANCH = new Meal(100005, LocalDateTime.of(2018, Month.JUNE, 12, 13, 0), "Юзер ланч", 900);

    public static final List<Meal> LIST_MEALS_BY_ADMIN_AFTER_DELETE = Arrays.asList(
            ADMIN_LANCH,
            new Meal(100004, LocalDateTime.of(2018, Month.MARCH, 12, 10, 0), "Админ завтрак", 810)
    );

    public static final List<Meal> LIST_MEALS_BY_ADMIN = Arrays.asList(
            new Meal(100003, LocalDateTime.of(2018, Month.MARCH, 12, 21, 0), "Админ ужин", 1500),
            ADMIN_LANCH,
            new Meal(100004, LocalDateTime.of(2018, Month.MARCH, 12, 10, 0), "Админ завтрак", 810)
    );

    public static final List<Meal> LIST_BETWEEN_DATE = Arrays.asList(
            new Meal(100006, LocalDateTime.of(2018, Month.JUNE, 12, 20, 0), "Юзер ужин", 700),
            USER_LANCH,
            new Meal(100007, LocalDateTime.of(2018, Month.JUNE, 12, 7, 0), "Юзер завтрак", 400)
    );

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
