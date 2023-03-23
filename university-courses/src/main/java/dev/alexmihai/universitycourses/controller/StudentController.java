package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/batch")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return service.saveStudents(students);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{studentId}/courses")
    public Student addStudentCourse(@PathVariable int studentId, @RequestBody StudentCourse studentCourses) {
        return service.addStudentCourse(studentId, studentCourses);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return service.getStudents();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }

    @DeleteMapping("/delete/{studentId}/courses/{courseId}")
    public String deleteStudentCourse(@PathVariable int studentId, @PathVariable int courseId) {
        return service.deleteStudentCourse(studentId, courseId);
    }
}
