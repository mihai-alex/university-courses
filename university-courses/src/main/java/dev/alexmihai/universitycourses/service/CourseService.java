package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired  // This annotation is used to inject the dependency - the CourseRepository object
    private CourseRepository repository;

    /*
        This method is used to save a course in the database.
        The method is automatically implemented by Spring.
        The argument is the course to be saved.
        The return value is the saved course.
     */
    public Course saveCourse(Course course) {
        return repository.save(course);  // This method is automatically implemented by Spring
    }

    public List<Course> saveCourses(List<Course> courses) {
        return repository.saveAll(courses);
    }

    public List<Course> getCourses() {
        return repository.findAll();
    }

    public Course getCourseById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteCourse(int id) {
        Course existingCourse = repository.findById(id).orElse(null);
        if (existingCourse == null) {
            return String.format("Course with id %d does not exist!", id);
        }
        repository.delete(existingCourse);
        return String.format("Course with id %d was deleted!", id);
    }

    public Course updateCourse(Course course) {
        Course existingCourse = repository.findById(course.getId()).orElse(null);
        existingCourse.setNumberOfCredits(course.getNumberOfCredits());
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setStartDate(course.getStartDate());
        existingCourse.setEndDate(course.getEndDate());
        existingCourse.setProfessor(course.getProfessor());
        return repository.save(existingCourse);
    }
}
