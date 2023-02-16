package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) throws JsonProcessingException {
        City city = cityRepository.findById(id).get();
        return weatherService.getCityWeather(city);
    }

    @GetMapping(path = "/search")
    public List<Map> getCities(@RequestParam(required = false) String name) throws JsonProcessingException {
        List<Map> cities = new ArrayList<>();
        List<City> result = name == null ? cityRepository.findAllByOrderByNameAsc() : cityRepository.findByNameStartingWithIgnoreCase(name);
        for (var city: result) {
            String temperature = weatherService.getCityWeather(city).get("temperature");
            cities.add(Map.of("temperature", temperature, "name", city.getName()));
        }
        return cities;
    }
    // END
}

