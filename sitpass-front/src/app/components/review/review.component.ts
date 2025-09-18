import { Component, Input, OnInit } from '@angular/core';
import { Review } from '../../types/review.type';
import { ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-review',
  standalone: false,
  templateUrl: './review.component.html',
  styleUrl: './review.component.scss'
})
export class ReviewComponent implements OnInit {


  @Input() facilityId!: number;
  reviews: Review[] = [];
  loading = false;
  error: string | null = null;

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    if (this.facilityId) {
      this.fetchReviews();
    }
  }

  fetchReviews(): void {
    this.loading = true;
    this.error = null;
    this.reviewService.getReviewsForFacility(this.facilityId).subscribe({
      next: (data) => {
        this.reviews = data;
        console.log(data)
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Failed to load reviews';
        this.loading = false;
      }
    });
  }

}
