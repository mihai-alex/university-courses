import { Component } from '@angular/core';
import { Professor } from '../professor';

@Component({
  selector: 'app-create-professor',
  templateUrl: './create-professor.component.html',
  styleUrls: ['./create-professor.component.css'],
})
export class CreateProfessorComponent {
  professor: Professor = new Professor();

  onSubmit() {
    console.log(this.professor);
  }
}
