import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateReview, Review } from '../../types/review.type';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = 'http://localhost:8080/api/facilities'; 

  constructor(private http: HttpClient) {}

  getReviewsForFacility(facilityId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.apiUrl}/facility/${facilityId}`);
  }

  createReview(facilityId: number, review: CreateReview): Observable<Review> {
    return this.http.post<Review>(`${this.apiUrl}/createReview/${facilityId}`, review);
  }
  
}
