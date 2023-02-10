package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> previousAvailableCourses(@PathVariable long id) {
        Course currentCourse = courseRepository.findById(id);
        String allAvailableCoursesPath = currentCourse.getPath();
        if (currentCourse == null || allAvailableCoursesPath == null) {
            return new ArrayList<>();
        } else {
            Iterable<Long> tmp = Arrays.stream(allAvailableCoursesPath.split("\\."))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return courseRepository.findAllById(tmp);
        }
    }
    // END

}
