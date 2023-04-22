import { Component } from '@angular/core';
import { Professor } from '../professor';
import { ProfessorService } from '../professor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-professor',
  templateUrl: './create-professor.component.html',
  styleUrls: ['./create-professor.component.css'],
})
export class CreateProfessorComponent {
  professor: Professor = new Professor();

  constructor(
    private professorService: ProfessorService,
    private router: Router
  ) {}

  saveProfessor() {
    // TODO: fix deprecated method call
    this.professorService.createProfessor(this.professor).subscribe(
      (data) => {
        console.log(data);
        this.goToProfessorList();
      },
      (error) => console.log(error)
    );
  }

  goToProfessorList() {
    this.router.navigate(['/professors']);
  }

  onSubmit() {
    console.log(this.professor);
    this.saveProfessor();
  }

  onCancel() {
    this.goToProfessorList();
  }
}
