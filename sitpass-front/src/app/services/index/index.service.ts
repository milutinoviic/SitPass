import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FacilityDocumentFile } from '../../types/facility.type';

@Injectable({
  providedIn: 'root'
})
export class IndexService {

   private baseUrl = 'http://localhost:8080/api/index'; 
   private fileApiUrl = 'http://localhost:8080/api/file'; // endpoint za fajlove
   

  constructor(private http: HttpClient) { }

  uploadDocumentFile(file: File, facilityId: number): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.baseUrl}/${facilityId}`, formData);
  }

  getDocumentFile(id: string): Observable<FacilityDocumentFile> {
    return this.http.get<FacilityDocumentFile>(`${this.baseUrl}/${id}`);
  }

 
  downloadFile(serverFilename: string): void {
  const url = `${this.fileApiUrl}/${serverFilename}`;
  this.http.get(url, { responseType: 'blob' }).subscribe({
    next: (blob) => {
      // kreira "link" za download
      const a = document.createElement('a');
      const objectUrl = URL.createObjectURL(blob);
      a.href = objectUrl;
      a.download = serverFilename; // ime fajla koje će se sačuvati
      a.click();
      URL.revokeObjectURL(objectUrl);
    },
    error: (err) => {
      console.error('Greška pri preuzimanju fajla:', err);
    }
  });
}

}
