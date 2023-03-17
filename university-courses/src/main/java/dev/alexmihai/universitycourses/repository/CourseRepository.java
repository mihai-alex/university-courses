package dev.alexmihai.universitycourses.repository;

import dev.alexmihai.universitycourses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    This interface is used to access the database.
    The methods are automatically implemented by Spring.
    The argument of the interface is the model class and the type of the primary key.
*/
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
