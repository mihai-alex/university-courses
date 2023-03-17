package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class CourseController {
    @Autowired
    private CourseService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add/course")
    // The @RequestBody annotation is used to get the JSON data from the request body
    public Course addCourse(@RequestBody Course course) {
        return service.saveCourse(course);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add/courses")
    // The @RequestBody annotation is used to get the JSON data from the request body
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return service.saveCourses(courses);
    }

    @GetMapping("/get/courses")
    public List<Course> findAllCourses() {
        return service.getCourses();
    }

    @GetMapping("/get/course/{id}")
    // The @PathVariable annotation is used to get the id from the URL
    public Course findCourseById(@PathVariable int id) {
        return service.getCourseById(id);
    }

    @PutMapping("/update/course")
    public Course updateCourse(@RequestBody Course course) {
        return service.updateCourse(course);
    }

    @DeleteMapping("/delete/course/{id}")
    public String deleteCourse(@PathVariable int id) {
        return service.deleteCourse(id);
    }
}
