import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Discipline } from '../types/discipline.type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DisciplineService {

   private apiUrl = 'http://localhost:8080/api/disciplines';

  constructor(private http: HttpClient) { }

  getDisciplinesByFacilityId(facilityId: number): Observable<Discipline[]> {
    return this.http.get<Discipline[]>(`${this.apiUrl}/facility/${facilityId}`);
  }

}
