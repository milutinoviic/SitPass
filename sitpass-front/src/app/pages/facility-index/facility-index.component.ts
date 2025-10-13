import { Component, Input } from '@angular/core';
import { IndexService } from '../../services/index/index.service';

@Component({
  selector: 'app-facility-index',
  standalone: false,
  templateUrl: './facility-index.component.html',
  styleUrl: './facility-index.component.scss'
})
export class FacilityIndexComponent {

  @Input() facilityId!: number; 

  selectedFile: File | null = null;
  uploadSuccess = false;
  errorMessage = '';

  constructor(private indexService: IndexService) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onUpload(): void {
    if (!this.selectedFile) {
      this.errorMessage = 'Molim odaberite fajl!';
      return;
    }

    if (!this.facilityId) {
      this.errorMessage = 'Molim unesite ID objekta!';
      return;
    }

    console.log('Facility ID:', this.facilityId);

    this.indexService.uploadDocumentFile(this.selectedFile, this.facilityId).subscribe({
      next: (response) => {
        console.log('Upload uspešan:', response);
        this.uploadSuccess = true;
        this.errorMessage = '';
      },
      error: (error) => {
        console.error('Greška pri uploadu:', error);
        this.uploadSuccess = false;
        this.errorMessage = 'Greška pri slanju fajla.';
      }
    });
  }

}
