package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        int currentHour = LocalDateTime.now().getHour();
        Daytime period = null;
        switch(currentHour) {
            case 6,7,8,9,10,11 -> period = new Morning();
            case 12,13,14,15,16,17 -> period = new Day();
            case 18,19,20,21,22 -> period = new Evening();
            case 0,1,2,3,4,5,23 -> period = new Night();
        }
        return period;
    }
}
// END
