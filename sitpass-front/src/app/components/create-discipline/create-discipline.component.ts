import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Discipline } from '../../types/discipline.type';
import { DisciplineService } from '../../services/disipline/discipline.service';

@Component({
  selector: 'app-create-discipline',
  standalone: false,
  templateUrl: './create-discipline.component.html',
  styleUrl: './create-discipline.component.scss'
})
export class CreateDisciplineComponent {

  disciplineForm: FormGroup;
  createdDiscipline?: Discipline;
  errorMessage?: string;
  disciplines: Discipline[] = [];

  constructor(private fb: FormBuilder, private disciplineService: DisciplineService) {
    this.disciplineForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(1)]]
    });
  }

  ngOnInit(): void {
    this.loadDisciplines();
  }

  onSubmit(): void {
    if (this.disciplineForm.invalid) return;

    this.disciplineService.createDiscipline(this.disciplineForm.value)
      .subscribe({
        next: (discipline) => {
          this.createdDiscipline = discipline;
          this.errorMessage = undefined;
          this.disciplineForm.reset();
          this.loadDisciplines();
        },
        error: (err) => {
          console.error(err);
          this.errorMessage = 'Došlo je do greške pri kreiranju discipline.';
        }
      });
  }

  loadDisciplines(): void {
    this.disciplineService.getAllDisciplines().subscribe({
      next: (list) => this.disciplines = list,
      error: (err) => console.error(err)
    });
  }

  deleteDiscipline(id: number): void {
    if (!confirm('Da li ste sigurni da želite obrisati ovu disciplinu?')) return;

    this.disciplineService.deleteDiscipline(id).subscribe({
      next: () => {
        this.disciplines = this.disciplines.filter(d => d.id !== id);
        if (this.createdDiscipline?.id === id) this.createdDiscipline = undefined;
      },
      error: (err) => {
        console.error(err);
        this.errorMessage = 'Došlo je do greške pri brisanju discipline.';
      }
    });
  }

}
