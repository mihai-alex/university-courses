package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.CourseByAvgProfSalaryDto;
import dev.alexmihai.universitycourses.dto.CourseGetAllDto;
import dev.alexmihai.universitycourses.dto.CourseGetByIdDto;
import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.repository.StudentRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> saveCourses(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    public List<CourseGetAllDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
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
        Course course = courseRepository.findById(id).orElse(null);
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
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (existingCourse == null) {
            return String.format("Course with id %d does not exist!", id);
        }
        courseRepository.delete(existingCourse);
        return String.format("Course with id %d was deleted!", id);
    }

    public Course updateCourse(Course course) {
        Course existingCourse = courseRepository.findById(course.getId()).orElse(null);
        existingCourse.setNumberOfCredits(course.getNumberOfCredits());
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setStartDate(course.getStartDate());
        existingCourse.setEndDate(course.getEndDate());
        existingCourse.setProfessor(course.getProfessor());
        return courseRepository.save(existingCourse);
    }

    public List<CourseByAvgProfSalaryDto> getCoursesByAvgProfSalaryDesc() {
        List<CourseByAvgProfSalaryDto> courses = courseRepository.findCoursesByAvgProfSalaryDesc();
        return courses;
    }

    public Course addCourseStudent(int courseId, StudentCourse studentCourse) {
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            return null;
        }
        Student existingStudent = studentRepository.findById(studentCourse.getStudent().getId()).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        StudentCourse newStudentCourse = new StudentCourse();
        newStudentCourse.setCourse(existingCourse);
        newStudentCourse.setStudent(existingStudent);
        newStudentCourse.setGrade(studentCourse.getGrade());
        newStudentCourse.setFeedback(studentCourse.getFeedback());
        existingStudent.getStudentCourses().add(newStudentCourse);
        return courseRepository.save(existingCourse);
    }

    public String deleteCourseStudent(int courseId, int studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
        if (existingStudent == null) {
            return String.format("Student with id %d does not exist!", studentId);
        }
        Course existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse == null) {
            return String.format("Course with id %d does not exist!", courseId);
        }
        StudentCourse existingStudentCourse = existingStudent.getStudentCourses().stream()
                .filter(studentCourse -> studentCourse.getCourse().getId() == courseId)
                .filter(studentCourse -> studentCourse.getStudent().getId() == studentId)
                .findFirst()
                .orElse(null);
        if (existingStudentCourse == null) {
            return String.format("Student with id %d is not enrolled in course with id %d!", studentId, courseId);
        }
        existingStudent.getStudentCourses().remove(existingStudentCourse);
        studentRepository.save(existingStudent);
        return String.format("Student with id %d was unenrolled from course with id %d!", studentId, courseId);
    }
}
