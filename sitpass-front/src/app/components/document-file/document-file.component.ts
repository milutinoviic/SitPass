import { Component, Input } from '@angular/core';
import { FacilityDocumentFile } from '../../types/facility.type';
import { IndexService } from '../../services/index/index.service';

@Component({
  selector: 'app-document-file',
  standalone: false,
  templateUrl: './document-file.component.html',
  styleUrl: './document-file.component.scss'
})
export class DocumentFileComponent {

  documentFile?: FacilityDocumentFile;
  errorMessage: string = '';
   @Input() facilityId!: number; 

  constructor(private documentFileService: IndexService) { }

   ngOnInit(): void {
    if (this.facilityId) {
      this.getDocument(this.facilityId);
    } else {
      this.errorMessage = 'Nije prosleđen dokument ID.';
    }
  }

  getDocument(id: number): void {
    this.documentFileService.getDocumentFile(id).subscribe({
      next: (file) => {
        this.documentFile = file;
      },
      error: (err) => {
        this.errorMessage = 'Greška pri učitavanju dokumenta: ' + err.message;
      }
    });
  }

  downloadDocument(): void {
    if (this.documentFile) {
      this.documentFileService.downloadFile(this.documentFile.serverFilename);
    }
  }

}
