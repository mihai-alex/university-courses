import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorListComponent } from './component/professor-list/professor-list.component';
import { ProfessorCreateComponent } from './component/professor-create/professor-create.component';
import { FormsModule } from '@angular/forms';
import { ProfessorUpdateComponent } from './component/professor-update/professor-update.component';
import { ProfessorDetailsComponent } from './component/professor-details/professor-details.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfessorListComponent,
    ProfessorCreateComponent,
    ProfessorUpdateComponent,
    ProfessorDetailsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
