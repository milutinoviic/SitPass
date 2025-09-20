import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateFacility, Facility, UpdateFacility } from '../../types/facility.type';

@Injectable({
  providedIn: 'root'
})
export class FacilityService {

  private baseUrl = 'http://localhost:8080/api/facilities';

  constructor(private http: HttpClient) { }

  getFacility(id: number): Observable<Facility> {
    return this.http.get<Facility>(`${this.baseUrl}/info/${id}`);
  }

  createFacility(dto: CreateFacility): Observable<Facility> {
    return this.http.post<Facility>(this.baseUrl, dto);
  }

  updateFacility(id: number, dto: UpdateFacility): Observable<Facility> {
    return this.http.put<Facility>(`${this.baseUrl}/${id}`, dto);
  }

}
