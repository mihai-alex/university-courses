import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorListComponent } from './professor-list/professor-list.component';
import { CreateProfessorComponent } from './create-professor/create-professor.component';
import { FormsModule } from '@angular/forms';
import { UpdateProfessorComponent } from './update-professor/update-professor.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfessorListComponent,
    CreateProfessorComponent,
    UpdateProfessorComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
