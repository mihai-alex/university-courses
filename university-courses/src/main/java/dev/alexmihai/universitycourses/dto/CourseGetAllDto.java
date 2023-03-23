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
public class CourseGetAllDto {
    private int id;
    private int numberOfCredits;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int professorId;
}
