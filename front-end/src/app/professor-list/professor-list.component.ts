import { Component } from '@angular/core';
import { Professor } from '../professor';
import { ProfessorService } from '../professor.service';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
})
export class ProfessorListComponent {
  professors!: Professor[];

  constructor(private professorService: ProfessorService) {}

  ngOnInit(): void {
    this.getProfessors();
  }

  private getProfessors() {
    this.professorService.getProfessorsList().subscribe((data) => {
      this.professors = data;
    });
  }
}
