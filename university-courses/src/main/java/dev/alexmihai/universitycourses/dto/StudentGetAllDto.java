package dev.alexmihai.universitycourses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data  // Lombok - generates getters, setters, toString, equals, hashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentGetAllDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int yearOfStudy;
}
