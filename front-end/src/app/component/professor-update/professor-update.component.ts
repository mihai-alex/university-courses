import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Professor } from 'src/app/model/professor';
import { ProfessorService } from 'src/app/service/professor.service';

@Component({
  selector: 'app-professor-update',
  templateUrl: './professor-update.component.html',
  styleUrls: ['./professor-update.component.css'],
})
export class ProfessorUpdateComponent implements OnInit {
  id!: number;
  professor: Professor = new Professor();

  constructor(
    private professorService: ProfessorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    // TODO: fix deprecated method
    this.professorService.getProfessorById(this.id).subscribe(
      (data) => {
        this.professor = data;
      },
      (error) => console.log(error)
    );
  }

  goToProfessorList() {
    this.router.navigate(['/professors']);
  }

  onSubmit() {
    this.professorService.updateProfessor(this.id, this.professor).subscribe(
      (data) => {
        this.goToProfessorList();
      },
      (error) => console.log(error)
    );
  }

  onCancel() {
    this.goToProfessorList();
  }
}
