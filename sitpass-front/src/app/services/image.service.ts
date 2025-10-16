import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Image {
  id: number;
  serverFilename: string;
  isDeleted: boolean;
  facility: any;
}


export interface FacilityImage {
  id: number;
  filename: string;
  base64?: string;
  error?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private baseUrl = 'http://localhost:8080/api/images';

  constructor(private http: HttpClient) {}

  // === Upload više slika za dati facility ===
  uploadImages(facilityId: number, files: File[]): Observable<Image[]> {
    const formData = new FormData();
    files.forEach(file => formData.append('files', file));
    return this.http.post<Image[]>(`${this.baseUrl}/${facilityId}`, formData);
  }

  getImages(facilityId: number): Observable<FacilityImage[]> {
    return this.http.get<FacilityImage[]>(`${this.baseUrl}/${facilityId}`);
  }
}
