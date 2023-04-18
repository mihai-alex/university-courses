import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorListComponent } from './professor-list/professor-list.component';
import { CreateProfessorComponent } from './create-professor/create-professor.component';

const routes: Routes = [
  { path: 'professors', component: ProfessorListComponent },
  { path: 'create-professor', component: CreateProfessorComponent },

  // TODO: possibly add home page component and route:
  { path: '', redirectTo: 'professors', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
