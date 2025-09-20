import { Component, Input, OnInit } from '@angular/core';
import { CreateExercise, Exercise } from '../../types/exercise.type';
import { ExerciseService } from '../../services/exercise/exercise.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TokenService } from '../../core/utils/token.service';

@Component({
  selector: 'app-exercise',
  standalone: false,
  templateUrl: './exercise.component.html',
  styleUrl: './exercise.component.scss'
})
export class ExerciseComponent {

  @Input() facilityId!: number;
   userId: number | null = null;

  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private exerciseService: ExerciseService,
    private tokenService:TokenService
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      startDateTime: ['', Validators.required],
      endDateTime: ['', Validators.required]
    });
    this.userId = this.tokenService.getUserId();
  }

  createExercise(): void {
    if (this.form.invalid) {
      alert('Please fill all fields.');
      return;
    }

    const start = new Date(this.form.value.startDateTime);
    const end = new Date(this.form.value.endDateTime);
    const now = new Date();

    if (start < now) {
      alert('Exercise cannot be scheduled in the past');
      return;
    }

    const minutes = (end.getTime() - start.getTime()) / (1000 * 60);
    if (!(minutes === 60 || minutes === 90)) {
      alert('Duration must be exactly 1h or 1.5h');
      return;
    }

    const startMinute = start.getMinutes();
    if (!(startMinute === 0 || startMinute === 30)) {
      alert('Start time must be on the hour or half past');
      return;
    }

    function formatLocalDateTime(date: Date): string {
      const yyyy = date.getFullYear();
      const mm = String(date.getMonth() + 1).padStart(2, '0');
      const dd = String(date.getDate()).padStart(2, '0');
      const hh = String(date.getHours()).padStart(2, '0');
      const min = String(date.getMinutes()).padStart(2, '0');
      const ss = String(date.getSeconds()).padStart(2, '0');

      return `${yyyy}-${mm}-${dd}T${hh}:${min}:${ss}`;
    }

    const exercise = {

      from: formatLocalDateTime(start),
      until: formatLocalDateTime(end),
      userId: this.userId || 1,
      facilityId: this.facilityId
    };
    console.log("-------------Ex---------------------")
    console.log(exercise);

    this.exerciseService.createExercise(exercise).subscribe({
      next: (data) => {
        alert('Successfully created exercise!');
        console.log(data);
      },
      error: (err) => {
        console.error('Error creating exercise:', err);
        alert('Failed to create exercise. ' + (err?.error?.message || ''));
      }
    });
  }

}
