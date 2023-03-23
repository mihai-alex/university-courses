package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    public Student saveStudent(Student student) {
        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setEmail(student.getEmail());
        newStudent.setPhone(student.getPhone());
        newStudent.setYearOfStudy(student.getYearOfStudy());
        newStudent.getStudentCourses().addAll((student.getStudentCourses().stream()
                .map(studentCourse -> {
                    Course course = courseRepository.findById(studentCourse.getCourse().getId()).orElse(null);
                    if (course == null) {
                        return null;
                    }
                    StudentCourse newStudentCourse = new StudentCourse();
                    newStudentCourse.setCourse(course);
                    newStudentCourse.setStudent(newStudent);
                    newStudentCourse.setGrade(studentCourse.getGrade());
                    newStudentCourse.setFeedback(studentCourse.getFeedback());
                    return newStudentCourse;
                })
                .collect(Collectors.toList())));
        return repository.save(newStudent);
    }

    public List<Student> saveStudents(List<Student> students) {
        return repository.saveAll(students);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteStudent(int id) {
        Student existingStudent = repository.findById(id).orElse(null);
        if (existingStudent == null) {
            return String.format("Student with id %d does not exist!", id);
        }
        repository.delete(existingStudent);
        return String.format("Student with id %d was deleted!", id);
    }

    public Student updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getId()).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setYearOfStudy(student.getYearOfStudy());
        return repository.save(existingStudent);
    }

    // attempt below:
    public Student addStudentCourse(int studentId, StudentCourse studentCourse) {
        Student existingStudent = repository.findById(studentId).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        Course existingCourse = courseRepository.findById(studentCourse.getCourse().getId()).orElse(null);
        if (existingCourse == null) {
            return null;
        }
        StudentCourse newStudentCourse = new StudentCourse();
        newStudentCourse.setCourse(existingCourse);
        newStudentCourse.setStudent(existingStudent);
        newStudentCourse.setGrade(studentCourse.getGrade());
        newStudentCourse.setFeedback(studentCourse.getFeedback());
        existingStudent.getStudentCourses().add(newStudentCourse);
        return repository.save(existingStudent);
    }

    public String deleteStudentCourse(int studentId, int courseId) {
        Student existingStudent = repository.findById(studentId).orElse(null);
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
        repository.save(existingStudent);
        return String.format("Student with id %d was unenrolled from course with id %d!", studentId, courseId);
    }
}
