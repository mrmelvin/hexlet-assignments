package exercise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcomeToSpring() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String nameController(@RequestParam(defaultValue = "World") String name) {
        String output = String.format("Hello, %s!", name);
        return output;
    }
}
// END
