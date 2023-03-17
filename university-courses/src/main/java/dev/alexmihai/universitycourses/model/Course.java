package dev.alexmihai.universitycourses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

// Lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

// JPA annotations
@Entity
@Table(name = "Courses")
public class Course {
    @Id  // primary key
    @GeneratedValue
    private int id;
    private int numberOfCredits;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    // Bidirectional relationship - one course can be taught by one professor (many-to-one)
    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference  // to avoid infinite recursion!!! (see Professor.java)
    private Professor professor;
}
