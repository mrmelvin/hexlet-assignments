package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start();
        baseUrl = "http://localhost:" + app.port();
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN

    @Test
    void testValidData() {
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", "John")
                .field("lastName", "Doe")
                .field("email", "johndoe@hotmail.com")
                .field("password", "9876")
                .asEmpty();
        assertThat(response.getStatus()).isEqualTo(302);

        User expectedUser = new QUser().email.equalTo("johndoe@hotmail.com").findOne();
        assertThat(expectedUser).isNotNull();
        assertThat(expectedUser.getFirstName()).isEqualTo("John");
        assertThat(expectedUser.getLastName()).isEqualTo("Doe");
        assertThat(expectedUser.getEmail()).isEqualTo("johndoe@hotmail.com");
        assertThat(expectedUser.getPassword()).isEqualTo("9876");
    }

    @Test
    void testInvalidPassword() {
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", "John")
                .field("lastName", "Doe")
                .field("email", "johndoe@hotmail.com")
                .field("password", "qw")
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getBody()).contains("Пароль должен содержать не менее 4 символов");
    }

    @Test
    void testInvalidEmail() {
        HttpResponse<String> response = Unirest.post(baseUrl + "/users")
                .header("Content-Type", "application/json")
                .field("firstName", "John")
                .field("lastName", "Doe")
                .field("email", "hotmail.ru")
                .field("password", "7193")
                .asString();
        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getBody()).contains("Должно быть валидным email");
    }
    // END
}
