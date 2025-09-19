import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateExercise, Exercise } from '../../types/exercise.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private apiUrl = 'http://localhost:8080/exercises';

  constructor(private http: HttpClient) {}

  createExercise(dto: CreateExercise): Observable<Exercise> {
    return this.http.post<Exercise>(this.apiUrl, dto);
  }
}
