import { Component, Input, OnInit } from '@angular/core';
import { Review } from '../../types/review.type';
import { ReviewService } from '../../services/review/review.service';
import { HttpClient } from '@angular/common/http';
import { TokenService } from '../../core/utils/token.service';
import { CommentReply } from '../../types/comment.type';


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

  replyContent: { [key: number]: string } = {}; 
  userId: number | null = null;

  constructor(private reviewService: ReviewService, private http: HttpClient,private tokenService:TokenService) {}

  ngOnInit(): void {
    if (this.facilityId) {
      this.fetchReviews();
    }
    this.userId = this.tokenService.getUserId();
  }

  fetchReviews(): void {
    this.loading = true;
    this.error = null;
    this.reviewService.getReviewsForFacility(this.facilityId).subscribe({
      next: (data) => {
        this.reviews = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Failed to load reviews';
        this.loading = false;
      }
    });
  }

  replyToComment(commentId: number): void {
  const content = this.replyContent[commentId];
  if (!content || !content.trim()) return;

  const dto: CommentReply = {
    commentId: commentId,
    userId: this.userId || 1,
    content: content
  };

  this.reviewService.replyToComment(dto).subscribe({
    next: () => {
      this.fetchReviews();
      this.replyContent[commentId] = '';
    },
    error: (err) => {
      console.error('Failed to reply to comment', err);
    }
  });

}
}
