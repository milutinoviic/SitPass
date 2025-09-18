import { Component, Input, OnInit } from '@angular/core';
import { Discipline } from '../../types/discipline.type';
import { DisciplineService } from '../../services/discipline.service';

@Component({
  selector: 'app-discipline',
  standalone: false,
  templateUrl: './discipline.component.html',
  styleUrl: './discipline.component.scss'
})
export class DisciplineComponent implements OnInit {


  @Input() facilityId!: number;   // facilityId prosleđuješ spolja
  disciplines: Discipline[] = [];
  loading = false;

  constructor(private disciplineService: DisciplineService) {}

  ngOnInit(): void {
    if (this.facilityId) {
      this.fetchDisciplines();
    }
  }

  fetchDisciplines(): void {
    this.loading = true;
    this.disciplineService.getDisciplinesByFacilityId(this.facilityId).subscribe({
      next: (data) => {
        this.disciplines = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching disciplines:', err);
        this.loading = false;
      }
    });
  }

}
