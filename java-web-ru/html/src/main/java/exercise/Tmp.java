package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Tmp {

    public static List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/users.json"),
                new TypeReference<List<Map<String, String>>>() {});
        // END
    }

    public static void main(String[] args) throws IOException {
        List<Map<String, String>> nestedList = getUsers();
        for (var user: nestedList) {
            if (user.get("id").equals("88")) {
                System.out.println(user.get("email"));
            }
        }

        String userId = Optional.of(nestedList.stream().filter(map -> map.get("id").equals("88")).toString()).orElse(null);
        System.out.println(userId);
    }
}

