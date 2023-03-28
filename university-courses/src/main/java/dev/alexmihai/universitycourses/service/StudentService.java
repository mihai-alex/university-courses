package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.StudentGetAllDto;
import dev.alexmihai.universitycourses.dto.StudentGetByIdDto;
import dev.alexmihai.universitycourses.model.Course;
import dev.alexmihai.universitycourses.model.Student;
import dev.alexmihai.universitycourses.model.StudentCourse;
import dev.alexmihai.universitycourses.repository.CourseRepository;
import dev.alexmihai.universitycourses.repository.StudentRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

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
        return studentRepository.save(newStudent);
    }

    public List<Student> saveStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    public List<StudentGetAllDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return null;
        }
        return ObjectMapperUtils.mapAll(students, StudentGetAllDto.class);
    }

    public StudentGetByIdDto getStudentById(int id) {
        // return studentRepository.findById(id).orElse(null);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return new StudentGetByIdDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getYearOfStudy(),
                student.getStudentCourses()
        );
    }

    public String deleteStudent(int id) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return String.format("Student with id %d does not exist!", id);
        }
        studentRepository.delete(existingStudent);
        return String.format("Student with id %d was deleted!", id);
    }

    public Student updateStudent(int id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setYearOfStudy(student.getYearOfStudy());
        return studentRepository.save(existingStudent);
    }

    // attempt below:
    public Student addStudentCourse(int studentId, StudentCourse studentCourse) {
        Student existingStudent = studentRepository.findById(studentId).orElse(null);
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
        return studentRepository.save(existingStudent);
    }

    public String deleteStudentCourse(int studentId, int courseId) {
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
