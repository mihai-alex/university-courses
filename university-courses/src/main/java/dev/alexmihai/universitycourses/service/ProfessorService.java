package dev.alexmihai.universitycourses.service;

import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.dto.ProfessorByNumStudsDto;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.repository.ProfessorRepository;
import dev.alexmihai.universitycourses.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public Professor saveProfessor(Professor professor) {
        return repository.save(professor);
    }

    public List<Professor> saveProfessors(List<Professor> professors) {
        return repository.saveAll(professors);
    }

    public List<ProfessorGetAllDto> getProfessors() {
        List<Professor> professors = repository.findAll();
        if (professors.isEmpty()) {
            return null;
        }
        return ObjectMapperUtils.mapAll(professors, ProfessorGetAllDto.class);
    }

    public Professor getProfessorById(int id) {
        return repository.findById(id).orElse(null);
    }

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

    public Professor updateProfessor(int id, Professor professor) {
        Professor existingProfessor = repository.findById(id).orElse(null);
        if (existingProfessor == null) {
            return null;
        }
        existingProfessor.setFirstName(professor.getFirstName());
        existingProfessor.setLastName(professor.getLastName());
        existingProfessor.setEmail(professor.getEmail());
        existingProfessor.setPhone(professor.getPhone());
        existingProfessor.setSalary(professor.getSalary());
        return repository.save(existingProfessor);
    }

    public List<ProfessorByNumStudsDto> getProfsByNumStudsDesc() {
        List<ProfessorByNumStudsDto> professors = repository.findProfsByNumStudsDesc();
        return professors;
    }
}
