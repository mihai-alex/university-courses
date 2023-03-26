package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.dto.CourseByAvgProfSalaryDto;
import dev.alexmihai.universitycourses.dto.CourseGetAllDto;
import dev.alexmihai.universitycourses.dto.CourseGetByIdDto;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    // The @RequestBody annotation is used to get the JSON data from the request body
    public Course addCourse(@RequestBody Course course) {
        return service.saveCourse(course);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/batch")
    // The @RequestBody annotation is used to get the JSON data from the request body
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return service.saveCourses(courses);
    }

    @GetMapping
    public List<CourseGetAllDto> findAllCourses() {
        return service.getCourses();
    }

    @GetMapping("/{id}")
    // The @PathVariable annotation is used to get the id from the URL
    public CourseGetByIdDto findCourseById(@PathVariable int id) {
        return service.getCourseById(id);
    }

    @PutMapping
    public Course updateCourse(@RequestBody Course course) {
        return service.updateCourse(course);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {
        return service.deleteCourse(id);
    }

    // statistics: show all courses ordered by the average salary of their professor
    @GetMapping("/stats-courses-by-avg-prof-salary-desc")
    public List<CourseByAvgProfSalaryDto> getCoursesByAvgProfSalaryDesc() {
        return service.getCoursesByAvgProfSalaryDesc();
    }
}
