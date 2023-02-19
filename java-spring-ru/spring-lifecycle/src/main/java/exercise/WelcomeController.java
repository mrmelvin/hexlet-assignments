package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Meal meal;

    @Autowired
    Daytime daytime;

    @GetMapping(path = "/daytime")
    public String getDaytime() {

        String now = daytime.getName();
        String currentMeal = meal.getMealForDaytime(now);
        return String.format("It is %s now. Enjoy your %s", now, currentMeal);
    }

}
// END
