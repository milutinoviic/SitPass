import { Component, Input, OnInit } from '@angular/core';
import { CreateExercise, Exercise } from '../../types/exercise.type';
import { ExerciseService } from '../../services/exercise/exercise.service';

@Component({
  selector: 'app-exercise',
  standalone: false,
  templateUrl: './exercise.component.html',
  styleUrl: './exercise.component.scss'
})
export class ExerciseComponent {
 
  @Input() facilityId!: number;

  newExercise: CreateExercise = {
    userId: 1,
    facilityId: 0,
    from: '',
    until: ''
  };

  createdExercise?: Exercise;
  loading = false;

  constructor(private exerciseService: ExerciseService) {}

  create() {
    if (!this.newExercise.from || !this.newExercise.until) {
      alert('Please fill in both From and Until!');
      return;
    }

    this.loading = true;
    this.newExercise.facilityId = this.facilityId;

    // konvertovanje lokalnog vremena u ISO format
    const fromDate = new Date(this.newExercise.from);
    const untilDate = new Date(this.newExercise.until);

    // Srbija je CET/CEST, koristi lokalno vreme browsera
    this.newExercise.from = fromDate.toISOString().slice(0, 19);
    this.newExercise.until = untilDate.toISOString().slice(0, 19);

    this.exerciseService.createExercise(this.newExercise).subscribe({
      next: (res) => {
        this.createdExercise = res;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        alert('Error creating exercise!');
      }
    });
  }

}
