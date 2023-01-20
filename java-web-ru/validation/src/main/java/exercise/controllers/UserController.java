package exercise.controllers;

import io.javalin.http.Handler;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.javalin.core.validation.Validator;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

import exercise.domain.User;
import exercise.domain.query.QUser;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
            .orderBy()
                .id.asc()
            .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
            .id.equalTo(id)
            .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String userFirstName = ctx.formParam("firstName");
        String userLastName = ctx.formParam("lastName");
        String userEmail = ctx.formParam("email");
        String userPassword = ctx.formParam("password");

        Validator<String> checkFirstName = ctx.formParamAsClass("firstName", String.class)
                .check(it -> !it.isEmpty(), "FirstName don't be empty!");
        Validator<String> checkLastName = ctx.formParamAsClass("lastName", String.class)
                .check(it -> !it.isEmpty(), "LastName don't be empty!");
        Validator<String> checkEmail = ctx.formParamAsClass("email", String.class)
                .check(it -> EmailValidator.getInstance().isValid(it), "Email not valid");
        Validator<String> checkPassword = ctx.formParamAsClass("password", String.class)
                .check(it -> StringUtils.isNumeric(it), "Password must be contains only numeric")
                .check(it -> it.length() > 4, "Password must be minimum 4 digits");

        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                checkFirstName,
                checkLastName,
                checkEmail,
                checkPassword
        );

        if (!errors.isEmpty()) {
            ctx.status(422);
            ctx.attribute("errors", errors);
            User invalidUser = new User(userFirstName, userLastName, userEmail, userPassword);
            ctx.attribute("user", invalidUser);
            ctx.render("users/new.html");
            return;
        }
        User user = new User(userFirstName, userLastName, userEmail, userPassword);
        user.save();
        ctx.attribute("flash", "Пользователь успешно создан");

        ctx.redirect("/users");

        // END
    };
}
