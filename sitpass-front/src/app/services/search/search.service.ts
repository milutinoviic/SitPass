import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SearchQueryDTO } from '../../types/index.type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private baseUrl = 'http://localhost:8080/api/search';

  constructor(private http: HttpClient) { }

  // simpleSearch(query: SearchQueryDTO, page: number = 0, size: number = 10): Observable<any> {
  //   const params = new HttpParams()
  //     .set('page', page)
  //     .set('size', size);
  //   return this.http.post<any>(`${this.baseUrl}/simple`, query, { params });
  // }

  simpleSearch(payload: SearchQueryDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/simple`, payload);
  }

  advancedSearch(query: SearchQueryDTO, page: number = 0, size: number = 10): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);
    return this.http.post<any>(`${this.baseUrl}/advance`, query, { params });
  }
}
