import { Component } from '@angular/core';
import { Professor } from '../professor';

@Component({
  selector: 'app-professor-list',
  templateUrl: './professor-list.component.html',
  styleUrls: ['./professor-list.component.css'],
})
export class ProfessorListComponent {
  professors!: Professor[];

  ngOnInit(): void {
    this.professors = [
      {
        id: 1,
        firstName: 'John',
        lastName: 'Doe',
        email: 'abc@gmail.com',
        phone: '555-555-5555',
        salary: 100000,
      },
      {
        id: 2,
        firstName: 'Jane',
        lastName: 'Doe',
        email: 'qwr@yahoo.com',
        phone: '555-555-5555',
        salary: 100000,
      },
    ];
  }
}
