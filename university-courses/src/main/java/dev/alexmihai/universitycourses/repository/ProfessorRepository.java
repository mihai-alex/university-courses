package dev.alexmihai.universitycourses.repository;

import dev.alexmihai.universitycourses.dto.AverageSalaryProfessorCoursesDto;
import dev.alexmihai.universitycourses.dto.ProfessorsByNumStudentsDto;
import dev.alexmihai.universitycourses.model.Professor;
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
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    /*
        This method is used to get all the professors with a salary greater than the given salary.
        The method is automatically implemented by Spring.
        The arguments are the name of the field in the model class and the comparison operator.
        The return value is a list of professors.
     */
    List<Professor> findBySalaryGreaterThan(int salary);

    @Query("SELECT new dev.alexmihai.universitycourses.dto.ProfessorsByNumStudentsDto(" +
            "p.id, p.firstName, p.lastName, COUNT(s.id)) " +
            "FROM Course c JOIN c.professor p " +
            "JOIN c.courseStudents s " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(s) DESC")
    List<ProfessorsByNumStudentsDto> findProfessorsByNumStudents();
}
