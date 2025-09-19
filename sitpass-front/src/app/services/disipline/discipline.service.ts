import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CreateDiscipline, Discipline, DisciplineToFromFacility } from '../../types/discipline.type';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

   private apiUrl = 'http://localhost:8080/api/disciplines';

  constructor(private http: HttpClient) { }

  getDisciplinesByFacilityId(facilityId: number): Observable<Discipline[]> {
    return this.http.get<Discipline[]>(`${this.apiUrl}/facility/${facilityId}`);
  }

  createDiscipline(discipline: CreateDiscipline): Observable<Discipline> {
    return this.http.post<Discipline>(`${this.apiUrl}/createDiscipline`, discipline);
  }

  deleteDiscipline(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  getAllDisciplines(): Observable<Discipline[]> {
    return this.http.get<Discipline[]>(`${this.apiUrl}/disciplineList`);
  }

  addDisciplinesToFacility(payload: DisciplineToFromFacility): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/addDisciplineListToFacility`, payload);
  }

  removeDisciplinesFromFacility(payload: DisciplineToFromFacility): Observable<void> {
    return this.http.request<void>('delete', `${this.apiUrl}/deleteDisciplinesFromFacility`, { body: payload });
  }

}
