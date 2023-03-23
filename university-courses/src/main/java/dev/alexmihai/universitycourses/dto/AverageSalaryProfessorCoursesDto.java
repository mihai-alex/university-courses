package dev.alexmihai.universitycourses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data  // Lombok - generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AverageSalaryProfessorCoursesDto {
    private int id;
    private String title;
    private int professorId;

    private double averageSalary;
}
