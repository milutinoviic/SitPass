import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AccountRequest, CreateAccountRequest, RejectRequest } from '../../types/accountRequest.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountRequestService {

 private apiUrl = 'http://localhost:8080/api/account-requests'; 

  constructor(private http: HttpClient) {}

  createAccountRequest(dto: CreateAccountRequest): Observable<AccountRequest> {
    return this.http.post<AccountRequest>(`${this.apiUrl}/createAccountRequest`, dto);
  }

  getAllRequests(): Observable<AccountRequest[]> {
    return this.http.get<AccountRequest[]>(`${this.apiUrl}/getAllAccountRequest`);
  }

  approveRequest(id: number): Observable<AccountRequest> {
    return this.http.patch<AccountRequest>(`${this.apiUrl}/${id}/approve`, {});
  }

  rejectRequest(dto: RejectRequest): Observable<AccountRequest> {
    return this.http.patch<AccountRequest>(`${this.apiUrl}/reject`, dto);
  }
}
