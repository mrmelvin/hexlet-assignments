package exercise;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
// BEGIN
@Component
// END
public class Meal {


    public String getMealForDaytime(String daytime) {

        switch (daytime) {
            case "morning":
                return "breakfast";
            case "day":
                return "lunch";
            case "evening":
                return "dinner";
            default:
                return "nothing :)";
        }
    }

    // Для самостоятельной работы
    // BEGIN
    
    // END
}
