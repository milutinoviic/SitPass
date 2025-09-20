import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FacilityService } from '../../services/facility/facility.service';
import { CreateFacility } from '../../types/facility.type';

@Component({
  selector: 'app-create-facility',
  standalone: false,
  templateUrl: './create-facility.component.html',
  styleUrl: './create-facility.component.scss'
})
export class CreateFacilityComponent {

  facilityForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private facilityService: FacilityService) {
    this.facilityForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      address: ['', [Validators.required, Validators.minLength(3)]],
      city: ['', [Validators.required, Validators.minLength(3)]],
    });
  }

  submit() {
    if (this.facilityForm.valid) {
      const dto: CreateFacility = this.facilityForm.value;
      this.facilityService.createFacility(dto).subscribe({
        next: res => {
          this.successMessage = 'Facility created successfully!';
          this.facilityForm.reset();
        },
        error: err => this.errorMessage = 'Error creating facility'
      });
    }
  }

}
