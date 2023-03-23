package dev.alexmihai.universitycourses.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

// JPA annotations
@Entity
@Table(name = "Professors")
public class Professor implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int salary;

    // Bidirectional relationship - one professor can teach many courses (one-to-many)
    // lazy fetch type - courses are loaded only when needed
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference  // to avoid infinite recursion!!! (see Course.java)
    private List<Course> courses = new ArrayList<>();
}
