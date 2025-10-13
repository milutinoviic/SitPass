import { Component } from '@angular/core';
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

  constructor(private documentFileService: IndexService) { }

  ngOnInit(): void {
    const documentId = '3';
    this.getDocument(documentId);
  }

  getDocument(id: string): void {
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
