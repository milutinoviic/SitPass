import { Injectable } from '@angular/core';
import { CheckManage, CreateManages, Manages, ManagesDetail } from '../../types/manages.type';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ManagesService {

  private apiUrl = 'http://localhost:8080/api/manages';

  constructor(private http: HttpClient) {}

  assignManager(dto: CreateManages): Observable<Manages> {
    return this.http.post<Manages>(`${this.apiUrl}/assign`, dto);
  }

  getAllManagesForFacility(facilityId: number): Observable<ManagesDetail[]> {
    return this.http.get<ManagesDetail[]>(`${this.apiUrl}/allManagesForFacility/${facilityId}`);
  }

  deleteManager(dto: CreateManages): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete`, { body: dto });
  }

  checkUserManagesFacility(dto: CheckManage): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/check`, dto);
  }
}
