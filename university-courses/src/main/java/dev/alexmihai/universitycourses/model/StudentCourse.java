package dev.alexmihai.universitycourses.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

// JPA annotations
@Entity
@Table(name = "StudentsCourses")
public class StudentCourse implements Serializable {
    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();

    @ManyToOne
    @MapsId("studentId")
    @JsonIgnore
    private Student student;  // many-to-many relationship

    @ManyToOne
    @MapsId("courseId")
    private Course course;  // many-to-many relationship

    // additional fields
    private int grade;
    private String feedback;
}
