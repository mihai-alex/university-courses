package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.dto.CourseByAvgProfSalaryDto;
import dev.alexmihai.universitycourses.dto.CourseGetAllDto;
import dev.alexmihai.universitycourses.dto.CourseGetByIdDto;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.StudentCourse;
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
    public Course addCourse(@RequestBody Course course) {
        return service.saveCourse(course);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/batch")
    public List<Course> addCourses(@RequestBody List<Course> courses) {
        return service.saveCourses(courses);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{courseId}/students")
    public Course addCourseStudent(@PathVariable int courseId, @RequestBody StudentCourse studentCourses) {
        return service.addCourseStudent(courseId, studentCourses);
    }

    @GetMapping
    public List<CourseGetAllDto> findAllCourses() {
        return service.getCourses();
    }

    @GetMapping("/{id}")
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
    
    @GetMapping("/stats-courses-by-avg-prof-salary-desc")
    public List<CourseByAvgProfSalaryDto> getCoursesByAvgProfSalaryDesc() {
        return service.getCoursesByAvgProfSalaryDesc();
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public String deleteCourseStudent(@PathVariable int courseId, @PathVariable int studentId) {
        return service.deleteCourseStudent(courseId, studentId);
    }
}
