import { Component, Input, OnInit } from '@angular/core';
import { Facility, UpdateFacility } from '../../types/facility.type';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacilityService } from '../../services/facility/facility.service';

@Component({
  selector: 'app-update-facility',
  standalone: false,
  templateUrl: './update-facility.component.html',
  styleUrl: './update-facility.component.scss'
})
export class UpdateFacilityComponent implements OnInit{


  @Input() facilityId!: number;
  facilityForm!: FormGroup;
  facility?: Facility;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private facilityService: FacilityService) {}

  ngOnInit() {
    this.initForm();

    if (this.facilityId) {
      this.facilityService.getFacility(this.facilityId).subscribe({
        next: (facility) => {
          this.facility = facility;
          this.facilityForm.patchValue({
            name: facility.name,
            description: facility.description,
            address: facility.address,
            city: facility.city
          });
        },
        error: () => this.errorMessage = 'Error loading facility'
      });
    }
  }

  initForm() {
    this.facilityForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(3)]],
      address: ['', [Validators.required, Validators.minLength(3)]],
      city: ['', [Validators.required, Validators.minLength(3)]],
    });
  }

  submit() {
    if (this.facilityForm.valid && this.facility) {
      const dto: UpdateFacility = this.facilityForm.value;
      this.facilityService.updateFacility(this.facility.id, dto).subscribe({
        next: () => this.successMessage = 'Facility updated successfully!',
        error: () => this.errorMessage = 'Error updating facility'
      });
    }
  }
}
