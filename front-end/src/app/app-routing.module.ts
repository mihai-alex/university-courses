import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorCreateComponent } from './component/professor-create/professor-create.component';
import { ProfessorListComponent } from './component/professor-list/professor-list.component';
import { ProfessorUpdateComponent } from './component/professor-update/professor-update.component';
import { ProfessorDetailsComponent } from './component/professor-details/professor-details.component';

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
