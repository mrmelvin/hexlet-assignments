package exercise;

import static exercise.Data.getUsers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Temp {

    public static void main(String[] args) {

        List<Map<String, String>> users = getUsers();
        String id = "51";

        Iterator<Map<String, String>> iter = users.iterator();
        while (iter.hasNext()) {
            Map<String, String> currentUser = iter.next();
            if (currentUser.get("id").equals(id)) {
                iter.remove();
            }
        }

        System.out.println(users);
    }
}
