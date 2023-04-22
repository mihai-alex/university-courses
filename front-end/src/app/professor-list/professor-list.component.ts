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

  createProfessor() {
    this.router.navigate(['professor-create']);
  }

  updateProfessor(id: number) {
    this.router.navigate(['professor-update', id]);
  }

  deleteProfessor(id: number) {
    this.professorService.deleteProfessor(id).subscribe(
      (data) => {
        console.log(data);
        this.getProfessors();
      },
      (error) => console.log(error)
    );
  }

  professorDetails(id: number) {
    this.router.navigate(['professor-details', id]);
  }
}
