import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Facility } from '../types/facility.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FacilityService {

  private baseUrl = 'http://localhost:8080/api/facilities';

  constructor(private http: HttpClient) { }

  getFacility(id: number): Observable<Facility> {
    return this.http.get<Facility>(`${this.baseUrl}/info/${id}`);
  }

}
