import { Component } from '@angular/core';
import { FacilityImage, Image, ImageService } from '../../services/image.service';

@Component({
  selector: 'app-facility-images',
  standalone: false,
  templateUrl: './facility-images.component.html',
  styleUrl: './facility-images.component.scss'
})
export class FacilityImagesComponent {

  facilityId = 1; // možeš promeniti po potrebi
  selectedFiles: File[] = [];
  images: Image[] = [];
  images1: FacilityImage[] = [];
  errorMessage = '';

  loading = false;

  constructor(private imageService: ImageService) {}

  ngOnInit(): void {
    this.loadImages();
  }

  onFileSelected(event: any) {
    this.selectedFiles = Array.from(event.target.files);
  }

  uploadImages() {
    if (this.selectedFiles.length === 0) return;
    this.loading = true;
    this.imageService.uploadImages(this.facilityId, this.selectedFiles).subscribe({
      next: (uploaded) => {
        this.images.push(...uploaded);
        this.selectedFiles = [];
        this.loading = false;
      },
      error: (err) => {
        console.error('Upload error', err);
        this.loading = false;
      }
    });
  }

   loadImages() {
    this.loading = true;
    this.imageService.getImages(this.facilityId).subscribe({
      next: (data) => {
        this.images1 = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Greška pri učitavanju slika';
        console.error(err);
        this.loading = false;
      }
    });
  }
 

}
