package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path pathToFile, Car initialCar) throws IOException {
        Files.writeString(pathToFile, initialCar.serialize(), StandardCharsets.UTF_8,
                                                              StandardOpenOption.CREATE,
                                                              StandardOpenOption.APPEND);
    }

    public static Car extract(Path pathToFile) throws IOException {
        return Car.unserialize(Files.readString(pathToFile));
    }
}
// END
