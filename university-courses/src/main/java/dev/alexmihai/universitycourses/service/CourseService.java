package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.AverageSalaryProfessorCoursesDto;
import dev.alexmihai.universitycourses.dto.CourseGetAllDto;
import dev.alexmihai.universitycourses.dto.CourseGetByIdDto;
import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CourseGetAllDto> getCourses() {
        List<Course> courses = repository.findAll();
        List<CourseGetAllDto> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            CourseGetAllDto courseDto = new CourseGetAllDto(
                    course.getId(),
                    course.getNumberOfCredits(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getStartDate(),
                    course.getEndDate(),
                    course.getProfessor().getId()
            );
            coursesDto.add(courseDto);
        }
        return coursesDto;
    }

    public CourseGetByIdDto getCourseById(int id) {
        Course course = repository.findById(id).orElse(null);
        if (course == null) {
            return null;
        }
        return new CourseGetByIdDto(
                course.getId(),
                course.getNumberOfCredits(),
                course.getTitle(),
                course.getDescription(),
                course.getStartDate(),
                course.getEndDate(),
                ObjectMapperUtils.map(course.getProfessor(), ProfessorGetAllDto.class),
                course.getCourseStudents()
        );
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

    public List<AverageSalaryProfessorCoursesDto> getCoursesOrderedByAverageSalary() {
        List<AverageSalaryProfessorCoursesDto> courses = repository.findCoursesOrderByAverageSalary();
        return courses;
    }
}
