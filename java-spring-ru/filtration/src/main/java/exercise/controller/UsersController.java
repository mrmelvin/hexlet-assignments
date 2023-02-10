package exercise.controller;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.querydsl.core.types.dsl.StringExpression;

import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        Iterable<User> output = null;
        if (firstName == null && lastName != null) {
            output = userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        } else if (firstName != null && lastName == null) {
            output = userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        } else if (firstName != null && lastName != null) {
            output = userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName)
                            .and(QUser.user.lastName.containsIgnoreCase(lastName)));
        } else {
            output = userRepository.findAll();
        }
        return output;
    }
    // END
}

