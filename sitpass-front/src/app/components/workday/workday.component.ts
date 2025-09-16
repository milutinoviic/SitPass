import { Component } from '@angular/core';
import { DayOfWeekEnum, WorkDay } from '../../types/workday.type';
import { WorkdayService } from '../../services/workday/workday.service';

@Component({
  selector: 'app-workday',
  standalone: false,
  templateUrl: './workday.component.html',
  styleUrl: './workday.component.scss'
})
export class WorkdayComponent {

  workdays: WorkDay[] = [];

  dayLabels: Record<DayOfWeekEnum, string> = {
    [DayOfWeekEnum.MONDAY]: 'Ponedeljak',
    [DayOfWeekEnum.TUESDAY]: 'Utorak',
    [DayOfWeekEnum.WEDNESDAY]: 'Sreda',
    [DayOfWeekEnum.THURSDAY]: 'Četvrtak',
    [DayOfWeekEnum.FRIDAY]: 'Petak',
    [DayOfWeekEnum.SATURDAY]: 'Subota',
    [DayOfWeekEnum.SUNDAY]: 'Nedelja',
  };

  constructor(private workDayService: WorkdayService) {}

  ngOnInit(): void {
    const facilityId = 1;
    this.workDayService.getCurrentWeek(facilityId).subscribe({
      next: (data) => this.workdays = data,
      error: (err) => console.error('Greška pri dohvatanju rasporeda:', err)
    });
  }

}
