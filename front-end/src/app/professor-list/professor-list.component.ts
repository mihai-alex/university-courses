import { Component } from '@angular/core';
import { Professor } from '../professor';
import { ProfessorService } from '../professor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
})
export class ProfessorListComponent {
  professors!: Professor[];

  constructor(
    private professorService: ProfessorService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getProfessors();
  }

  private getProfessors() {
    this.professorService.getProfessorsList().subscribe((data) => {
      this.professors = data;
    });
  }

  updateProfessor(id: number) {
    this.router.navigate(['update-professor', id]);
  }
}
