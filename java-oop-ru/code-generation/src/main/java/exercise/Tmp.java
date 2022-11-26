package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class Tmp {


    public static void main(String[] args) throws IOException {
        User owner = new User(1, "Ivan", "P", 25);
        Car car = new Car(1, "bmv", "x5", "black", owner);
    }
}
