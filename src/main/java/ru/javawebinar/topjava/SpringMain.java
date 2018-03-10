package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
//            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
//            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println(mealRestController.get(1, AuthorizedUser.id()));
            ProfileRestController profileRestController = appCtx.getBean(ProfileRestController.class);

            MealRestController mealRestController1 = appCtx.getBean(MealRestController.class);
            System.out.println(mealRestController.getAll(2).size());
        }

    }
}
