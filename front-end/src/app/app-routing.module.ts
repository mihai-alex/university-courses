import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorListComponent } from './professor-list/professor-list.component';
import { ProfessorCreateComponent } from './professor-create/professor-create.component';
import { ProfessorUpdateComponent } from './professor-update/professor-update.component';
import { ProfessorDetailsComponent } from './professor-details/professor-details.component';

const routes: Routes = [
  { path: 'professors', component: ProfessorListComponent },
  { path: 'professor-create', component: ProfessorCreateComponent },
  { path: 'professor-update/:id', component: ProfessorUpdateComponent },
  { path: 'professor-details/:id', component: ProfessorDetailsComponent },

  // TODO: possibly add home page component and route:
  { path: '', redirectTo: 'professors', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
