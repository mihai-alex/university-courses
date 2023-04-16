import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from './professor';

@Injectable({
  providedIn: 'root',
})
export class ProfessorService {
  private baseURL = 'http://localhost:8080/api/professors';

  constructor(private httpClient: HttpClient) {}

  getProfessorsList(): Observable<Professor[]> {
    return this.httpClient.get<Professor[]>(`${this.baseURL}`);
  }
}
