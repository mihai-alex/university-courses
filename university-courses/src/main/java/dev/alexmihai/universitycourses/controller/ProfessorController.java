package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.dto.ProfessorGetAllDto;
import dev.alexmihai.universitycourses.dto.ProfessorsByNumStudentsDto;
import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    // The @RequestBody annotation is used to get the JSON data from the request body
    public Professor addProfessor(@RequestBody Professor professor) {
        return service.saveProfessor(professor);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/batch")
    // The @RequestBody annotation is used to get the JSON data from the request body
    public List<Professor> addProfessors(@RequestBody List<Professor> professors) {
        return service.saveProfessors(professors);
    }

    @GetMapping
    public List<ProfessorGetAllDto> findAllProfessors() {
        return service.getProfessors();
    }

    @GetMapping("/{id}")
    // The @PathVariable annotation is used to get the id from the URL
    public Professor findProfessorById(@PathVariable int id) {
        return service.getProfessorById(id);
    }

    // This method is used to get all the professors with a salary greater than the given salary
    @RequestMapping(value = "/salary-greater/{salary}", method = RequestMethod.GET)
    public List<ProfessorGetAllDto> findBySalaryGreaterThan(@PathVariable int salary) {
        return service.findBySalaryGreaterThan(salary);
    }

    @PutMapping
    public Professor updateProfessor(@RequestBody Professor professor) {
        return service.updateProfessor(professor);
    }

    @DeleteMapping("/{id}")
    public String deleteProfessor(@PathVariable int id) {
        return service.deleteProfessor(id);
    }

    @GetMapping("/stats-profs-by-num-studs-desc")
    public List<ProfessorsByNumStudentsDto> getProfsByNumStudsDesc() {
        return service.getProfsByNumStudsDesc();
    }
}
