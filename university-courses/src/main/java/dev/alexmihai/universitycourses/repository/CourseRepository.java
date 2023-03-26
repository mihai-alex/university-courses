package dev.alexmihai.universitycourses.repository;

import dev.alexmihai.universitycourses.dto.CourseByAvgProfSalaryDto;
import dev.alexmihai.universitycourses.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    This interface is used to access the database.
    The methods are automatically implemented by Spring.
    The argument of the interface is the model class and the type of the primary key.
*/
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT new dev.alexmihai.universitycourses.dto.CourseByAvgProfSalaryDto(" +
            "c.id, c.title, c.professor.id, AVG(p.salary)) " +
            "FROM Course c JOIN c.professor p " +
            "GROUP BY c.id " +
            "ORDER BY AVG(p.salary) DESC")
    List<CourseByAvgProfSalaryDto> findCoursesByAvgProfSalaryDesc();
}
