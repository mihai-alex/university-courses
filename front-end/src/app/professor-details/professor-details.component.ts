import { Component } from '@angular/core';
import { Professor } from '../professor';
import { ActivatedRoute } from '@angular/router';
import { ProfessorService } from '../professor.service';

@Component({
  selector: 'app-professor-details',
  templateUrl: './professor-details.component.html',
  styleUrls: ['./professor-details.component.css'],
})
export class ProfessorDetailsComponent {
  id!: number;
  professor!: Professor;

  constructor(
    private route: ActivatedRoute,
    private professorService: ProfessorService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.professor = new Professor();
    this.professorService.getProfessorById(this.id).subscribe((data) => {
      this.professor = data;
    });
  }
}
