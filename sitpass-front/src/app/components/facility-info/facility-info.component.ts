import { Component, Input, OnInit } from '@angular/core';
import { Facility } from '../../types/facility.type';
import { ActivatedRoute } from '@angular/router';
import { FacilityService } from '../../services/facility/facility.service';

@Component({
  selector: 'app-facility-info',
  standalone: false,
  templateUrl: './facility-info.component.html',
  styleUrl: './facility-info.component.scss'
})
export class FacilityInfoComponent implements OnInit {

  
  @Input() facilityId!: number;  
  facility: Facility | null = null;
  loading = false;
  error: string | null = null;

  constructor(private facilityService: FacilityService) {}

  ngOnInit(): void {
    if (this.facilityId) {
      this.fetchFacility();
    }
  }

  fetchFacility(): void {
    this.loading = true;
    this.error = null;

    this.facilityService.getFacility(this.facilityId).subscribe({
      next: (data) => {
        this.facility = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching facility:', err);
        this.error = 'Failed to load facility info';
        this.loading = false;
      }
    });
  }

}
