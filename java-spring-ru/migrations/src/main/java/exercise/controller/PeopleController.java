package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "")
    public List<Map<String, Object>> getAllPersons() {
        String query = "SELECT * FROM person";
        List<Map<String, Object>> list = jdbc.queryForList(query);
        return list;
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Map<String, Object> getPerson(@PathVariable String id) {
        String query = "SELECT first_name, last_name FROM person WHERE id = ?";
        Map <String, Object> mp = jdbc.queryForMap(query, new Object[]{id});
        return mp;
    }


    // END
}
