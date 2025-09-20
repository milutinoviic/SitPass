import { Component } from '@angular/core';
import { Exercise } from '../../types/exercise.type';
import { ExerciseService } from '../../services/exercise/exercise.service';
import { TokenService } from '../../core/utils/token.service';

@Component({
  selector: 'app-exercise-list',
  standalone: false,
  templateUrl: './exercise-list.component.html',
  styleUrl: './exercise-list.component.scss'
})
export class ExerciseListComponent {
 exercises: Exercise[] = [];
  userId: number | null = null; 

  constructor(
    private exerciseService: ExerciseService,
    private tokenService: TokenService
  ) {}

  ngOnInit(): void {
    this.userId = this.tokenService.getUserId();
    this.loadExercises();
  }

 loadExercises(): void {
  if (this.userId !== null) {
    this.exerciseService.getExercisesByUser(this.userId).subscribe({
      next: (data) => {
        console.log('Data from API:', data); 
        this.exercises = data;
      },
      error: (err) => console.error('Greška pri učitavanju vežbi', err)
    });
  }
}


  canDelete(from: string): boolean {
    const now = new Date();
    const exerciseStart = new Date(from);
    const oneDayBefore = new Date(exerciseStart);
    oneDayBefore.setDate(oneDayBefore.getDate() - 1);

    // dugme je enabled samo ako je sada pre nego što je 1 dan pre početka
    return now < oneDayBefore;
  }

  onDelete(exerciseId: number): void {
    if (confirm('Da li ste sigurni da želite da obrišete vežbu?')) {
      this.exerciseService.deleteExercise(exerciseId).subscribe({
        next: () => {
          // uklonimo iz liste da se UI osveži
          this.exercises = this.exercises.filter(e => e.id !== exerciseId);
        },
        error: (err) => console.error('Greška pri brisanju vežbe', err)
      });
    }
  }

}
