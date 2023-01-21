package exercise;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

import static org.assertj.core.api.Assertions.assertThat;


public class Temp {

    public static void main(String[] args) {
        Javalin app;
        app = App.getApp();
        app.start();
        String baseUrl = "http://localhost:" + app.port();

        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", "John")
                .field("lastName", "Doe")
                .field("email", "hotmail.ru")
                .field("password", "7193")
                .asString();
        String content = response.getBody();

        app.stop();

        System.out.println(content);
    }
}
