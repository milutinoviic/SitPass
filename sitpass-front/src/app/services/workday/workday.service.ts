import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WorkDay } from '../../types/workday.type';

@Injectable({
  providedIn: 'root'
})
export class WorkdayService {

  private apiUrl = 'http://localhost:8080/api/workdays';

  constructor(private http: HttpClient) {}

  getCurrentWeek(facilityId: number): Observable<WorkDay[]> {
    return this.http.get<WorkDay[]>(`${this.apiUrl}/${facilityId}/current-week`);
  }
}
