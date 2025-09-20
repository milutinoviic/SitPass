import { Component, Input, OnInit } from '@angular/core';
import { ReviewService } from '../../services/review/review.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateReview } from '../../types/review.type';
import { TokenService } from '../../core/utils/token.service';

@Component({
  selector: 'app-create-review',
  standalone: false,
  templateUrl: './create-review.component.html',
  styleUrl: './create-review.component.scss'
})
export class CreateReviewComponent implements OnInit{

  @Input() facilityId!: number;
  userId: number | null = null;

  reviewForm: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private facilityService: ReviewService,private tokenService:TokenService) {
    this.reviewForm = this.fb.group({
      equipment: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      staff: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      hygiene: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      space: [null, [Validators.required, Validators.min(1), Validators.max(5)]],
      commentText: ['']
    });
  }

  ngOnInit(): void {
    this.userId = this.tokenService.getUserId();
  }

  submitReview() {
    if (this.reviewForm.invalid || !this.facilityId) return;

    const formValues = this.reviewForm.value;

    const comment = formValues.commentText
      ? { text: formValues.commentText }
      : undefined;

    const review: CreateReview = {
      userId: this.userId || 1, 
      rate: {
        equipment: formValues.equipment,
        staff: formValues.staff,
        hygiene: formValues.hygiene,
        space: formValues.space
      },
      comment: comment
    };

    this.facilityService.createReview(this.facilityId, review).subscribe({
      next: () => {
        this.successMessage = 'Review successfully created!';
        this.errorMessage = '';
        this.reviewForm.reset();
      },
      error: (err) => {
        this.errorMessage = 'Failed to create review.';
        this.successMessage = '';
        console.error(err);
      }
    });
  }
}
