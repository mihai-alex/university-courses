import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfessorListComponent } from './professor-list/professor-list.component';
import { ProfessorCreateComponent } from './professor-create/professor-create.component';
import { FormsModule } from '@angular/forms';
import { ProfessorUpdateComponent } from './professor-update/professor-update.component';
import { ProfessorDetailsComponent } from './professor-details/professor-details.component';

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
