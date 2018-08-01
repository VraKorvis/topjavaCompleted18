package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@Component
public class UserValidator implements Validator{

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz)||User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String email;
        Integer id;
        if(target instanceof UserTo) {
            UserTo userTo = (UserTo) target;
            email = userTo.getEmail();
            id = userTo.getId();
        }
        else {
            User user = (User) target;
            email = user.getEmail();
            id = user.getId();
        }

        try {
            User existedUser = userService.getByEmail(email);
            if(existedUser != null && !existedUser.getId().equals(id))
                errors.rejectValue("email", "user.exists", "User with this email already exists");
        }
        catch (NotFoundException e){
            return;
        }
    }
}