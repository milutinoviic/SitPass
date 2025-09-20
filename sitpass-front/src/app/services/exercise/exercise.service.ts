import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateExercise, Exercise, PastVisitsRequest } from '../../types/exercise.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  private apiUrl = 'http://localhost:8080/exercises';

  constructor(private http: HttpClient) { }

  getExercisesByUser(userId: number): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(`${this.apiUrl}/user/${userId}`);
  }

  createExercise(dto: CreateExercise): Observable<Exercise> {
    return this.http.post<Exercise>(this.apiUrl, dto);
  }

  deleteExercise(exerciseId: number) {
    return this.http.delete<void>(`${this.apiUrl}/${exerciseId}`);
  }

  getPastVisits(request: PastVisitsRequest): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/past-visits`, request);
  }

}
