package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.AverageSalaryProfessorCoursesDto;
import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.dto.ProfessorsByNumStudentsDto;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.repository.ProfessorRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired  // This annotation is used to inject the dependency - the CourseRepository object
    private ProfessorRepository repository;

    /*
        This method is used to save a course in the database.
        The method is automatically implemented by Spring.
        The argument is the course to be saved.
        The return value is the saved course.
     */
    public Professor saveProfessor(Professor professor) {
        return repository.save(professor);  // This method is automatically implemented by Spring
    }

    public List<Professor> saveProfessors(List<Professor> professors) {
        return repository.saveAll(professors);
    }

    public List<ProfessorGetAllDto> getProfessors() {
        List<Professor> professors = repository.findAll();
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public Professor getProfessorById(int id) {
        return repository.findById(id).orElse(null);
    }

    // This method is used to get all the professors with a salary greater than the given salary
    public List<ProfessorGetAllDto> findBySalaryGreaterThan(int salary) {
        List<Professor> professors = repository.findBySalaryGreaterThan(salary);
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public String deleteProfessor(int id) {
        Professor existingProfessor = repository.findById(id).orElse(null);
        if (existingProfessor == null) {
            return String.format("Professor with id %d does not exist!", id);
        }
        repository.delete(existingProfessor);
        return String.format("Professor with id %d was deleted!", id);
    }

    public Professor updateProfessor(Professor professor) {
        Professor existingProfessor = repository.findById(professor.getId()).orElse(null);
        existingProfessor.setFirstName(professor.getFirstName());
        existingProfessor.setLastName(professor.getLastName());
        existingProfessor.setEmail(professor.getEmail());
        existingProfessor.setPhone(professor.getPhone());
        existingProfessor.setSalary(professor.getSalary());
        return repository.save(existingProfessor);
    }

    public List<ProfessorsByNumStudentsDto> getProfessorsByNumStudents() {
        List<ProfessorsByNumStudentsDto> professors = repository.findProfessorsByNumStudents();
        return professors;
    }
}
