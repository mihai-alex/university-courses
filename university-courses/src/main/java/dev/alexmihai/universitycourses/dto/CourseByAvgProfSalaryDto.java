package dev.alexmihai.universitycourses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data  // Lombok - generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseByAvgProfSalaryDto {
    private int id;
    private String title;
    private int professorId;

    private double averageSalary;
}
