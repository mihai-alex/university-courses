package dev.alexmihai.universitycourses.controller;

import dev.alexmihai.universitycourses.model.Professor;
import dev.alexmihai.universitycourses.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add/professor")
    // The @RequestBody annotation is used to get the JSON data from the request body
    public Professor addProfessor(@RequestBody Professor professor) {
        return service.saveProfessor(professor);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add/professors")
    // The @RequestBody annotation is used to get the JSON data from the request body
    public List<Professor> addProfessors(@RequestBody List<Professor> professors) {
        return service.saveProfessors(professors);
    }

    @GetMapping("/get/professors")
    public List<Professor> findAllProfessors() {
        return service.getProfessors();
    }

    @GetMapping("/get/professor/{id}")
    // The @PathVariable annotation is used to get the id from the URL
    public Professor findProfessorById(@PathVariable int id) {
        return service.getProfessorById(id);
    }

    // This method is used to get all the professors with a salary greater than the given salary
    @RequestMapping(value = "/get/professors/salary/greaterthan/{salary}", method = RequestMethod.GET)
    public List<Professor> findBySalaryGreaterThan(@PathVariable int salary) {
        return service.findBySalaryGreaterThan(salary);
    }

    @PutMapping("/update/professor")
    public Professor updateProfessor(@RequestBody Professor professor) {
        return service.updateProfessor(professor);
    }

    @DeleteMapping("/delete/professor/{id}")
    public String deleteProfessor(@PathVariable int id) {
        return service.deleteProfessor(id);
    }
}
