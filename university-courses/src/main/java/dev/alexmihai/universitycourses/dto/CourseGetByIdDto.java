package dev.alexmihai.universitycourses.dto;

import dev.alexmihai.universitycourses.model.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data  // Lombok - generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseGetByIdDto {
    private int id;
    private int numberOfCredits;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    ProfessorGetAllDto professor;
    private List<StudentCourse> courseStudents;
}
